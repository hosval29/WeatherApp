package com.hosvalandroiddev.weather_data.repository

import com.hosvalandroiddev.core.util.Resource
import com.hosvalandroiddev.weather_data.BuildConfig
import com.hosvalandroiddev.weather_data.mapper.toLocation
import com.hosvalandroiddev.weather_data.mapper.toLocationTime
import com.hosvalandroiddev.weather_data.mapper.toLocationWeather
import com.hosvalandroiddev.weather_data.remote.api.WeatherApi
import com.hosvalandroiddev.weather_domain.model.Location
import com.hosvalandroiddev.weather_domain.model.LocationTime
import com.hosvalandroiddev.weather_domain.model.LocationWeather
import com.hosvalandroiddev.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {

    override fun getLocationByCoordinates(query: String): Flow<Resource<List<Location>>> = flow {
        emit(Resource.Loading(isLoading = true))

        try {
            val locations = weatherApi.getLocationByCoordinates(
                query = query,
                limit = "5"
            ).map { it.toLocation() }

            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(locations))
            return@flow
        } catch (e: HttpException) {
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Error(message = "Oops, algo salió mal, vuelve a intentar."))
            return@flow
        } catch (e: IOException) {
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Error(message = "Error de conexión al servidor, valida tu conexión a internet."))
            return@flow
        }
    }

    override fun getTimeByLocation(latitude: Double, longitude: Double): Flow<Resource<LocationTime>> = flow {
        emit(Resource.Loading(isLoading = true))

        try {
            val timeLocation = weatherApi.getTimeByLocation(
                key = BuildConfig.API_KEY_TIME,
                format = "json",
                by = "position",
                lat = latitude,
                lng = longitude,
                url = BuildConfig.BASE_URL_TIME
            ).toLocationTime()
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(timeLocation))
            return@flow
        } catch (e: HttpException) {
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Error(message = "Oops, algo salió mal, vuelve a intentar."))
            return@flow
        } catch (e: IOException) {
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Error(message = "Error de conexión al servidor, valida tu conexión a internet."))
            return@flow
        }
    }

    override fun getWeatherByLocation(latitude: Double, longitude: Double): Flow<Resource<LocationWeather>> = flow {
        emit(Resource.Loading(isLoading = true))

        try {
            val locations = weatherApi.getWeatherByLocation(
                lat = latitude,
                lon = longitude,
                units = "metric"
            ).toLocationWeather()
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Success(locations))
            return@flow
        } catch (e: HttpException) {
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Error(message = "Oops, algo salió mal, vuelve a intentar."))
            return@flow
        } catch (e: IOException) {
            emit(Resource.Loading(isLoading = false))
            emit(Resource.Error(message = "Error de conexión al servidor, valida tu conexión a internet."))
            return@flow
        }
    }
}