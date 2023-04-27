package com.hosvalandroiddev.weather_domain.model

data class Location(
    val country: String? = "",
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0,
    val name: String? = "",
    val state: String? = ""
)
