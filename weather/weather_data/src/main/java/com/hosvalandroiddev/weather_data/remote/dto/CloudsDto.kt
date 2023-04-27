package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class CloudsDto(
    @field:Json(name = "all") val all: Int?
)
