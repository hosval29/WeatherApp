package com.hosvalandroiddev.weather_domain.use_case

import com.hosvalandroiddev.core.util.Resource
import com.hosvalandroiddev.weather_domain.model.LocationWeather
import com.hosvalandroiddev.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherByLocation(private val weatherRepository: WeatherRepository) {
    operator fun invoke(latitude: Double, longitude: Double) : Flow<Resource<LocationWeather>> {
        return weatherRepository.getWeatherByLocation(latitude, longitude)
    }
}