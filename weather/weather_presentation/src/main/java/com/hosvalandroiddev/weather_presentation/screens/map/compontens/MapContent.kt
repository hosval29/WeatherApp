package com.hosvalandroiddev.weather_presentation.screens.map.compontens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.hosvalandroiddev.core.R
import com.hosvalandroiddev.core.util.Utils
import com.hosvalandroiddev.weather_domain.model.LocationWeather
import com.hosvalandroiddev.weather_presentation.screens.weather.components.WeatherItem
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MapContent(
    locationWeather: LocationWeather,
    timesTamp: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF293F6F),
                        Color(0xFF7EA0E5)
                    )
                )
            )
    ) {

        val (textCity, textTime, textWeather, textTemp, detail) = createRefs()
        val verticalGuide = createGuidelineFromTop(0.40f)

        Text(
            text = locationWeather.name ?: "Test",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(textCity) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, margin = 22.dp)
            }
        )

        Text(
            text = stringResource(R.string.acualized) + Utils.convertFromTimeToFormatted(
                locationWeather.dt ?: 0L
            ),
            color = Color.White, modifier = Modifier.constrainAs(textTime) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(textCity.bottom, margin = 4.dp)
            }
        )

        Text(
            text = "${
                locationWeather.weather?.firstOrNull()?.description?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale(
                            "es",
                            "es_MX"
                        )
                    ) else it.toString()
                }
            }",
            color = Color.White,
            modifier = Modifier.constrainAs(textWeather) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(textTime.bottom, margin = 32.dp)
            }
        )

        Text(
            text = "${locationWeather.main?.temp?.toInt()}°C",
            fontSize = 62.sp,
            color = Color.White,
            modifier = Modifier.constrainAs(textTemp) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(textWeather.bottom, margin = 22.dp)
            }
        )

        LazyVerticalGrid(
            modifier = Modifier.constrainAs(detail) {
                top.linkTo(verticalGuide)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            },
            columns = GridCells.Adaptive(100.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                item {
                    WeatherItem(
                        modifier = Modifier,
                        image = R.drawable.sunrise,
                        title = stringResource(R.string.sunrice),
                        time = SimpleDateFormat(
                            "hh:mm a",
                            Locale.ENGLISH
                        ).format(Date(locationWeather.sys?.sunrise?.times(1000) ?: 0L))
                    )
                }
                item {
                    WeatherItem(
                        modifier = Modifier,
                        image = R.drawable.sunset,
                        title = stringResource(R.string.suntset),
                        time = SimpleDateFormat(
                            "hh:mm a",
                            Locale.ENGLISH
                        ).format(Date(locationWeather.sys?.sunset?.times(1000) ?: 0L))
                    )
                }
                item {
                    WeatherItem(
                        modifier = Modifier,
                        image = R.drawable.ic_timer,
                        title = stringResource(R.string.hour),
                        time = timesTamp.removeRange(0..10)
                    )
                }
                item {
                    WeatherItem(
                        modifier = Modifier,
                        image = R.drawable.wind,
                        title = stringResource(R.string.wind),
                        time = "${locationWeather.wind?.speed}"
                    )
                }
                item {
                    WeatherItem(
                        modifier = Modifier,
                        image = R.drawable.presure,
                        title = stringResource(R.string.presure),
                        time = "${locationWeather.main?.pressure}"
                    )
                }
                item {
                    WeatherItem(
                        modifier = Modifier,
                        image = R.drawable.humidity,
                        title = stringResource(R.string.humidity),
                        time = "${locationWeather.main?.humidity}"
                    )
                }
            }
        )
    }
}