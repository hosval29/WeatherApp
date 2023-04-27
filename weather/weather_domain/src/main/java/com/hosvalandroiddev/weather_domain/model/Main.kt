package com.hosvalandroiddev.weather_domain.model

data class Main(
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double? = 0.0,
    val tempMax: Double,
    val tempMin: Double
)
