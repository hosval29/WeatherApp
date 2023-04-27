package com.hosvalandroiddev.weather_data.mapper

import com.hosvalandroiddev.weather_data.remote.dto.LocationDto
import com.hosvalandroiddev.weather_domain.model.Location

fun LocationDto.toLocation() : Location {
    return Location(
        name = name,
        latitude = latitude,
        longitude = longitude,
        state = state,
        country = country
    )
}