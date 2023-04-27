package com.hosvalandroiddev.weather_data.mapper

import com.hosvalandroiddev.weather_data.remote.dto.*
import com.hosvalandroiddev.weather_domain.model.*

fun LocationWeatherDto.toLocationWeather(): LocationWeather {
    return LocationWeather(
        clouds = clouds?.toCloud(),
        coordinated = coordinated.toCoordinated(),
        main = main.toMain(),
        name = name,
        dt = dt?.toLong(),
        sys = sys.toSys(),
        timezone = timezone,
        weather = weather.map { it.toWeather() },
        wind = wind.toWind()
    )
}

fun CloudsDto.toCloud(): Clouds {
    return Clouds(
        all = all
    )
}

fun MainDto.toMain(): Main {
    return Main(
        feelsLike = feelsLike,
        humidity = humidity,
        pressure = pressure,
        temp = temp,
        tempMax = tempMax,
        tempMin = tempMin
    )
}

fun SysDto.toSys(): Sys {
    return Sys(
        country = country,
        id = id,
        sunrise = sunrise,
        sunset = sunset,
        type = type
    )
}

fun WeatherDto.toWeather(): Weather {
    return Weather(
        description = description,
        icon = icon,
        id = id,
        main = main
    )
}

fun WindDto.toWind(): Wind {
    return Wind(
        deg = deg,
        speed =speed
    )
}

fun CoordinatedDto.toCoordinated(): Coordinated {
    return Coordinated(
        latitude = latitude,
        longitude = longitude
    )
}
