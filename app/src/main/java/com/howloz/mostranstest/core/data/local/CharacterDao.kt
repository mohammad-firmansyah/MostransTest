package com.howloz.mostranstest.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.howloz.mostranstest.core.data.local.entity.Character
import com.howloz.mostranstest.core.data.local.entity.Location
import com.howloz.mostranstest.core.data.local.entity.LocationAndCharacter

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters: List<Character>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocation(location: Location):Long

    @Query("SELECT * from characters")
    fun getAllCharacters():LiveData<List<Character>>

    @Query("SELECT * from characters")
    fun getAllCharactersNotLiveData():List<Character>

    @Query("SELECT id from locations where name = :name")
    fun getLocationByName(name:String):Long

    @Query("SELECT * from locations")
    fun getAllLocations():LiveData<List<Location>>

    @Query("SELECT * from locations WHERE id = :id")
    fun getLocation(id:Long):LiveData<Location>

    @Query("UPDATE characters SET location_id = :locId WHERE id = :charId")
    fun updateChar(charId: Long, locId: Long): Int


    @Transaction
    @Query("SELECT * from characters where location_id = :locId")
    fun getAllCharByLocation(locId:Long):LiveData<List<Character>>

    //deleteAll
    @Query("DELETE FROM characters")
    fun deleteAllCharacters()

    //deleteAll
    @Query("DELETE FROM locations")
    fun deleteAllLocation()

    @Query("DELETE FROM locations WHERE id = :id")
    fun deleteLocation(id: Long)
}