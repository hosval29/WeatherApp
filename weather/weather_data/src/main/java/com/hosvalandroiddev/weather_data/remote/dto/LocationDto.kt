package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = "country") val country: String? = "",
    @field:Json(name = "lat") val latitude: Double? = 0.0,
    @field:Json(name = "local_names") val localNames: LocalNameDto,
    @field:Json(name = "lon") val longitude: Double,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "state") val state: String
)
