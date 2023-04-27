package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class SysDto(
    @field:Json(name = "country") val country: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "sunrise") val sunrise: Long,
    @field:Json(name = "sunset") val sunset: Long,
    @field:Json(name = "type") val type: Int
)
