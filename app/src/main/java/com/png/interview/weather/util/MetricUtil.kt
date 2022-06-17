package com.png.interview.weather.util

import com.png.interview.weather.api.model.Current
import com.png.interview.weather.api.model.Day

object MetricUtil {

    private fun getTemperatureAbbr(isMetric: Boolean): String {
       return if (isMetric) "C" else "F"
    }

    private fun getSpeedAbbr(isMetric: Boolean): String {
        return if (isMetric) "KPH" else "MPH"
    }


    fun getMaxTemp(isMetric: Boolean, day: Day): String {
        return "${if (isMetric) day.maxtemp_c else day.maxtemp_f} ${getTemperatureAbbr(isMetric)}"
    }

    fun getMinTemp(isMetric: Boolean, day: Day): String {
        return "${if (isMetric) day.mintemp_c else day.mintemp_c} ${getTemperatureAbbr(isMetric)}"
    }

    fun getWindSpeed(isMetric: Boolean,day: Day): String {
        return "${if (isMetric) day.maxwind_kph else day.maxwind_mph} ${getSpeedAbbr(isMetric)}"
    }

    fun getCurrentTemp(isMetric: Boolean, current: Current): String {
        return "${if (isMetric) current.temp_c else current.temp_f} ${getSpeedAbbr(isMetric)}"
    }

    fun getCurrentSpeed(isMetric: Boolean, current: Current): String {
        return "${if (isMetric) current.gust_kph else current.gust_mph} ${getSpeedAbbr(isMetric)}"
    }

}