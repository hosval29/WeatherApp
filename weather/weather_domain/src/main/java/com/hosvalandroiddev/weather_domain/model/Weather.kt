package com.hosvalandroiddev.weather_domain.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
