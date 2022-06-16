package com.png.interview.weather.util

import android.content.SharedPreferences
import com.png.interview.weather.api.model.Current
import com.png.interview.weather.api.model.Day
import com.png.interview.weather.ui.binder.IS_METRIC

data class MetricUtil(
    private val sharedPreferences: SharedPreferences
) {
    private val isMetric = sharedPreferences.getBoolean(IS_METRIC, false)
    private val temperatureAbbr = if (isMetric) "C" else "F"
    private val speedAbbr = if (isMetric) "KPH" else "MPH"

    fun getMaxTemp(day: Day): String {
        return "${if (isMetric) day.maxtemp_c else day.maxtemp_f} $temperatureAbbr"
    }

    fun getMinTemp(day: Day): String {
        return "${if (isMetric) day.mintemp_c else day.mintemp_c} $temperatureAbbr"
    }

    fun getWindSpeed(day: Day): String {
        return "${if (isMetric) day.maxwind_kph else day.maxwind_mph} $speedAbbr"
    }

    fun getCurrentTemp(current: Current): String {
        return "${if (isMetric) current.temp_c else current.temp_f} $temperatureAbbr"
    }

    fun getCurrentSpeed(current: Current): String {
        return "${if (isMetric) current.gust_kph else current.gust_mph} $speedAbbr"
    }

}