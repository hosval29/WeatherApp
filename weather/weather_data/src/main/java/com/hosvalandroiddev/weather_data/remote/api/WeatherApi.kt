package com.hosvalandroiddev.weather_data.remote.api

import com.hosvalandroiddev.weather_data.remote.dto.LocationDto
import com.hosvalandroiddev.weather_data.remote.dto.LocationTimeDto
import com.hosvalandroiddev.weather_data.remote.dto.LocationWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherApi {

    @GET("/geo/1.0/direct?")
    suspend fun getLocationByCoordinates(
        @Query("q") query: String,
        @Query("limit") limit: String
    ) : List<LocationDto>

    @GET
    suspend fun getTimeByLocation(
        @Url url: String,
        @Query("key") key: String,
        @Query("format") format: String,
        @Query("by") by: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
    ) : LocationTimeDto

    @GET("data/2.5/weather?")
    suspend fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
    ) : LocationWeatherDto
}