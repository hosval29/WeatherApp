package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class CoordinatedDto(
    @field:Json(name = "lat") val latitude: Double,
    @field:Json(name = "lon") val longitude: Double
)
