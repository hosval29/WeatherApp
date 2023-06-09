package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "description") val description: String,
    @field:Json(name = "icon") val icon: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "main") val main: String
)
