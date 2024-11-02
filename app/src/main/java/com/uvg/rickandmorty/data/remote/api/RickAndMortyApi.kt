package com.uvg.rickandmorty.data.remote.api

import com.uvg.rickandmorty.data.remote.dto.CharacterResponse
import com.uvg.rickandmorty.data.remote.dto.LocationResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("location")
    suspend fun getLocations(): LocationResponse
}
