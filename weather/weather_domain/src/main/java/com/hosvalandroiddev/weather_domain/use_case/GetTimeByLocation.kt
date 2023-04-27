package com.hosvalandroiddev.weather_domain.use_case

import com.hosvalandroiddev.core.util.Resource
import com.hosvalandroiddev.weather_domain.model.LocationTime
import com.hosvalandroiddev.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetTimeByLocation(private val weatherRepository: WeatherRepository) {
    operator fun invoke(latitude: Double, longitude: Double) : Flow<Resource<LocationTime>> {
        return weatherRepository.getTimeByLocation(latitude, longitude)
    }
}