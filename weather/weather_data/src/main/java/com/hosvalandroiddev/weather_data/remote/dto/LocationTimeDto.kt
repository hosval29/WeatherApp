package com.hosvalandroiddev.weather_data.remote.dto

import com.squareup.moshi.Json

data class LocationTimeDto(
    @field:Json(name = "abbreviation")
    val abbreviation: String? = "",
    @field:Json(name = "cityName")
    val cityName: String? = "",
    @field:Json(name = "countryCode")
    val countryCode: String? = "",
    @field:Json(name = "countryName")
    val countryName: String? = "",
    @field:Json(name = "dst")
    val dst: String? = "",
    @field:Json(name = "formatted")
    val formatted: String? = "",
    @field:Json(name = "gmtOffset")
    val gmtOffset: Int? = 0,
    @field:Json(name = "message")
    val message: String? = "",
    @field:Json(name = "nextAbbreviation")
    val nextAbbreviation: String? = "",
    @field:Json(name = "regionName")
    val regionName: String? = "",
    @field:Json(name = "status")
    val status: String? = "",
    @field:Json(name = "timestamp")
    val timestamp: Long? = 0L,
    @field:Json(name = "zoneEnd")
    val zoneEnd: Int? = 0,
    @field:Json(name = "zoneName")
    val zoneName: String? = "",
    @field:Json(name = "zoneStart")
    val zoneStart: Int? = 0
)
