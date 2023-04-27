package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class LocationWeatherDto(
    @field:Json(name = "base") val base: String?,
    @field:Json(name = "clouds") val clouds: CloudsDto?,
    @field:Json(name = "cod") val cod: Int? = 0,
    @field:Json(name = "coord") val coordinated: CoordinatedDto,
    @field:Json(name = "dt") val dt: Int? = 0,
    @field:Json(name = "id") val id: Int? = 0,
    @field:Json(name = "main") val main: MainDto,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "sys") val sys: SysDto,
    @field:Json(name = "timezone") val timezone: Int,
    @field:Json(name = "visibility") val visibility: Int,
    @field:Json(name = "weather") val weather: List<WeatherDto>,
    @field:Json(name = "wind") val wind: WindDto
)
