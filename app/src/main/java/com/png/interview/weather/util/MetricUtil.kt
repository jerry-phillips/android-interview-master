package com.png.interview.weather.util

import android.content.SharedPreferences
import com.png.interview.weather.api.model.Current
import com.png.interview.weather.api.model.Day
import com.png.interview.weather.ui.binder.IS_METRIC

data class MetricUtil(
    var sharedPreferences: SharedPreferences
) {

    private val isMetric = sharedPreferences.getBoolean(IS_METRIC, false)
    val temperatureAbbr = if (isMetric) "C" else "F"
    val speedAbbr = if (isMetric) "KPH" else "MPH"

    fun getMaxTemp(day: Day): Double {
        return if (isMetric) day.maxtemp_c else day.maxtemp_f
    }

    fun getMinTemp(day: Day): Double {
        return if (isMetric) day.mintemp_c else day.mintemp_c
    }

    fun getWindSpeed(day: Day): Double {
        return if (isMetric) day.maxwind_kph else day.maxwind_mph
    }

    fun getCurrentTemp(current: Current): Double {
        return if (isMetric) current.temp_c else current.temp_f
    }

    fun getCurrentSpeed(current: Current): Double {
        return if (isMetric) current.gust_kph else current.gust_mph
    }

}