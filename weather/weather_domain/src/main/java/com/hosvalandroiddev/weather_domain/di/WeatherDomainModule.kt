package com.hosvalandroiddev.weather_domain.di

import com.hosvalandroiddev.weather_domain.repository.WeatherRepository
import com.hosvalandroiddev.weather_domain.use_case.GetLocationByCoordinates
import com.hosvalandroiddev.weather_domain.use_case.GetTimeByLocation
import com.hosvalandroiddev.weather_domain.use_case.GetWeatherByLocation
import com.hosvalandroiddev.weather_domain.use_case.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object WeatherDomainModule {

    @Provides
    @ViewModelScoped
    fun providesWeatherUseCase(weatherRepository: WeatherRepository) : WeatherUseCase {
        return WeatherUseCase(
            getLocationByCoordinates = GetLocationByCoordinates(weatherRepository = weatherRepository),
            getTimeByLocation = GetTimeByLocation(weatherRepository = weatherRepository),
            getWeatherByLocation = GetWeatherByLocation(weatherRepository = weatherRepository)
        )
    }
}