package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class MainDto(
    @field:Json(name = "feels_like") val feelsLike: Double,
    @field:Json(name = "humidity") val humidity: Int,
    @field:Json(name = "pressure") val pressure: Int,
    @field:Json(name = "temp") val temp: Double? = 0.0,
    @field:Json(name = "temp_max") val tempMax: Double,
    @field:Json(name = "temp_min") val tempMin: Double
)
