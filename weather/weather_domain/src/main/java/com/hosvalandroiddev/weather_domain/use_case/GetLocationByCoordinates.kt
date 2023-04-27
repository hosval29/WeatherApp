package com.hosvalandroiddev.weather_domain.use_case

import com.hosvalandroiddev.core.util.Resource
import com.hosvalandroiddev.weather_domain.model.Location
import com.hosvalandroiddev.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetLocationByCoordinates(private val weatherRepository: WeatherRepository) {
    operator fun invoke(query: String) : Flow<Resource<List<Location>>> {
        return weatherRepository.getLocationByCoordinates(query)
    }
}