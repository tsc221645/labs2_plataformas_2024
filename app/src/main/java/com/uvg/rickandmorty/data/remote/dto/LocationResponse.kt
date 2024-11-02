package com.uvg.rickandmorty.data.remote.dto

data class LocationResponse(
    val results: List<LocationDto>
)

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
