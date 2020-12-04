package com.example.desafiowebservices.services

import com.example.desafiowebservices.entities.Msg
import com.example.desafiowebservices.entities.MsgCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service{

    @GET("characters/1009610/comics")
    suspend fun getComicsListAPI(
        @Query("offset")p1: Int,
        @Query("limit")p2: Int,
        @Query("ts") p3: String,
        @Query("apikey")p4: String,
        @Query("hash")p5: String
    ): Msg

    @GET("characters/1009610")
    suspend fun getCharacterAPI(
        @Query("offset")p1: Int,
        @Query("limit")p2: Int,
        @Query("ts") p3: String,
        @Query("apikey")p4: String,
        @Query("hash")p5: String
    ): MsgCharacter

}

val urlMarvelAPI = "https://gateway.marvel.com/v1/public/"

val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(urlMarvelAPI)
    .build()

val service: Service = retrofit.create(Service::class.java)