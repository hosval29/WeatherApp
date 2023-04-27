package com.hosvalandroiddev.weather_domain.use_case

data class WeatherUseCase(
    val getLocationByCoordinates: GetLocationByCoordinates,
    val getTimeByLocation: GetTimeByLocation,
    val getWeatherByLocation: GetWeatherByLocation
)