package com.hosvalandroiddev.core.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun convertFromTimeToFormatted(dt: Long): String {
        return SimpleDateFormat(
            "dd/MM/yyyy hh:mm a",
            Locale("es", "es_MX")
        ).format(
            Date(dt.times(1000))
        )
    }
}