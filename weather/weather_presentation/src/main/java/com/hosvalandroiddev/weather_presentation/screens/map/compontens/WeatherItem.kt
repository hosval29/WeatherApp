package com.hosvalandroiddev.weather_presentation.screens.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    image: Int,
    title: String,
    time: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(color = (Color.DarkGray.copy(alpha = 0.16f)))
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = title,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(32.dp)
        )
        Text(text = title)
        Text(text = time)
    }
}