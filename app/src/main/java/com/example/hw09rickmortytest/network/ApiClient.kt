package com.example.hw09rickmortytest.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    /**
     https://rickandmortyapi.com/api/character/?page=1
     The Retrofit builder will need a base url so we extract that
     from our link and create the base url variable of type String
     */

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * Next we create a variable for the moshi builder,
     * adding a converter to it
     */
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * Then we create an instance of Retrofit by lazy
     * so it can initialized only when it is needed
     * pass the base url and the moshi variables created above
     */
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

/**
 * Below the object class lets create an interface to define how
 * Retrofit talks to the service using the Get method
 */
//An interface called ApiService
interface ApiService {
    /**Then we create a fetchCharacters method
     * annotate with @Get passing the character pass just like in our api link
     * above to tell the server send us those characters
     */
    @GET("character")
    fun fetchCharacters(@Query("page") page: String): Call<CharacterResponce>
}