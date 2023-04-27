package com.hosvalandroiddev.weather_data.mapper

import com.hosvalandroiddev.weather_data.remote.dto.LocationTimeDto
import com.hosvalandroiddev.weather_domain.model.LocationTime

fun LocationTimeDto.toLocationTime(): LocationTime {
    return LocationTime(
        formatted = formatted,
    )
}