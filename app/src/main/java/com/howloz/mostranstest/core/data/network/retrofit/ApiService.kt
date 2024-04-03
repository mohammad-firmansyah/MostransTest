package com.howloz.mostranstest.core.data.network.retrofit

import com.howloz.mostranstest.core.data.network.response.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/character/")
    fun getAllChars(): Call<CharacterResponse>
}