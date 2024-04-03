package com.howloz.mostranstest.core.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.howloz.mostranstest.core.data.local.CharacterDao
import com.howloz.mostranstest.core.data.local.MostransDatabase
import com.howloz.mostranstest.core.data.local.Result
import com.howloz.mostranstest.core.data.local.entity.Character
import com.howloz.mostranstest.core.data.local.entity.Location
import com.howloz.mostranstest.core.data.local.entity.LocationAndCharacter
import com.howloz.mostranstest.core.data.network.response.CharacterResponse
import com.howloz.mostranstest.core.data.network.retrofit.ApiConfig
import com.howloz.mostranstest.core.data.network.retrofit.ApiService
import com.howloz.mostranstest.core.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CharacterRepository(
    val application: Context,
    private val apiService: ApiService,
    private val newsDao: CharacterDao,
    private val appExecutors: AppExecutors
) {

    private val result = MediatorLiveData<Result<List<Character>>>()
    private val resultLocation = MediatorLiveData<Result<List<Location>>>()
    private val resultLocationAndCharacter = MediatorLiveData<Result<List<LocationAndCharacter>>>()

    private lateinit var characterDao :CharacterDao
    private val exexcutorService : ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = MostransDatabase.getDatabaseInstance(application)
        characterDao = db.characterDao()
    }

    fun insertCharacter(char : Character){
        exexcutorService.execute{characterDao.insertCharacter(char)}
    }

    fun getLocations():LiveData<Result<List<Location>>>{
        val localData = characterDao.getAllLocations()
        resultLocation.addSource(localData) { data: List<Location> ->
            resultLocation.value = Result.Success(data)
        }
        return resultLocation


    }

    fun insertLocation(loc : Location,charId:Long){
        exexcutorService.execute{
            val insertedId = characterDao.insertLocation(loc)
            if(insertedId == (-1).toLong()){
                val id = characterDao.getLocationByName(loc.name)
                characterDao.updateChar(locId = id, charId = charId)
            }else{
                characterDao.updateChar(locId = insertedId, charId = charId)

            }
        }
    }

    fun getAllChars(): LiveData<Result<List<Character>>> {
        result.value=Result.Loading
        val client = ApiConfig.provideApiService().getAllChars()
        client.enqueue(object:Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {

                if(response.isSuccessful){
                    val data = response.body()?.results
                    val listChar = ArrayList<Character>()

                    AppExecutors().diskIO.execute{
                        data?.forEach{char ->
                            val character = Character(
                                id = null,
                                name = char?.name.toString(),
                                status = char?.status.toString(),
                                image = char?.image.toString(),
                                location_id = null
                            )

                            listChar.add(character)
                        }

                        if(characterDao.getAllCharactersNotLiveData().size == 0){
                            characterDao.deleteAllCharacters()
                            characterDao.insertCharacters(listChar)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }

        })

        val localData = characterDao.getAllCharacters()
        result.addSource(localData) { data: List<Character> ->
            result.value = Result.Success(data)
        }
        return result

    }

    fun deleteLocation(id: Long) {
        exexcutorService.execute {
            characterDao.deleteLocation(id)
        }
    }

    fun getAllCharByLocation(loc_id:Long):LiveData<Result<List<Character>>>{
        val localData = characterDao.getAllCharByLocation(loc_id)
        result.addSource(localData) { data: List<Character> ->
            result.value = Result.Success(data)
        }
        return result
    }
    companion object {
        @Volatile
        private var instance: CharacterRepository? = null
        fun getInstance(
            application: Context,
            apiService: ApiService,
            newsDao: CharacterDao,
            appExecutors: AppExecutors
        ): CharacterRepository =
            instance ?: synchronized(this) {
                instance ?: CharacterRepository(application,apiService, newsDao, appExecutors)
            }.also { instance = it }
    }
}