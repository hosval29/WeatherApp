package com.hosvalandroiddev.weather_domain.model

data class LocationWeather(
    val clouds: Clouds? = null,
    val coordinated: Coordinated? = null,
    val main: Main? = null,
    val name: String? = null,
    val dt: Long? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val weather: List<Weather?>? = emptyList(),
    val wind: Wind? = null
)