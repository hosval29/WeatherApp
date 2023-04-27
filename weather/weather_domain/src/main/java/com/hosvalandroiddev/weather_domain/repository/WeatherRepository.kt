package com.hosvalandroiddev.weather_domain.repository

import com.hosvalandroiddev.core.util.Resource
import com.hosvalandroiddev.weather_domain.model.Location
import com.hosvalandroiddev.weather_domain.model.LocationTime
import com.hosvalandroiddev.weather_domain.model.LocationWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getLocationByCoordinates(query: String): Flow<Resource<List<Location>>>
    fun getTimeByLocation(latitude: Double, longitude: Double) : Flow<Resource<LocationTime>>
    fun getWeatherByLocation(latitude: Double, longitude: Double): Flow<Resource<LocationWeather>>
}