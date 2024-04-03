package com.howloz.mostranstest.core.di

import android.content.Context
import com.howloz.mostranstest.core.data.CharacterRepository
import com.howloz.mostranstest.core.data.local.MostransDatabase
import com.howloz.mostranstest.core.data.network.retrofit.ApiConfig
import com.howloz.mostranstest.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): CharacterRepository {
        val apiService = ApiConfig.provideApiService()
        val database = MostransDatabase.getDatabaseInstance(context)
        val dao = database.characterDao()
        val appExecutors = AppExecutors()
        return CharacterRepository.getInstance(context ,apiService, dao, appExecutors)
    }
}