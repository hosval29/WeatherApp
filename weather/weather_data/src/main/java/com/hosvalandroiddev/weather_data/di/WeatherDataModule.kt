package com.hosvalandroiddev.weather_data.di

import android.app.Application
import android.content.Context
import com.hosvalandroiddev.weather_data.BuildConfig
import com.hosvalandroiddev.core.R
import com.hosvalandroiddev.weather_data.remote.api.WeatherApi
import com.hosvalandroiddev.weather_data.repository.WeatherRepositoryImpl
import com.hosvalandroiddev.weather_domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherDataModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(app: Application) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(interceptor = getHeaderInterceptor(app.applicationContext))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    private fun getHeaderInterceptor(context: Context): Interceptor = Interceptor { chain ->
        var request = chain.request()

        val url = request.url
            .newBuilder()
            .addQueryParameter(
                name = context.getString(R.string.api_key),
                value = BuildConfig.API_KEY
            )
            .build()

        request = request
            .newBuilder()
            .url(url)
            .build()

        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit) : WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesWeatherRepository(weatherApi: WeatherApi) : WeatherRepository {
        return WeatherRepositoryImpl(
            weatherApi = weatherApi
        )
    }
}