package com.example.retrofitexample.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    // Base Url
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    // Moshi Builder
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    //Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}

// Create an Interface - How Retrofit will talk with service
interface ApiService {
    @GET("character")
    fun fetchCharacter(@Query("page") page: String): Call<CharacterResponse>
}