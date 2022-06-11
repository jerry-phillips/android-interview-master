package com.png.interview.weather.util

import android.content.SharedPreferences
import com.png.interview.weather.ui.binder.IS_METRIC

data class MetricUtil (
    var sharedPreferences: SharedPreferences
) {

        private val isMetric = sharedPreferences.getBoolean(IS_METRIC, false)
        val temperature = if (isMetric) "C" else "F"
        val speed = if (isMetric) "KPH" else "MPH"

}