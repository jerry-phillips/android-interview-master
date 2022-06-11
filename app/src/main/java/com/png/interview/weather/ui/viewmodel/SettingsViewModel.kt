package com.png.interview.weather.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.png.interview.weather.ui.binder.IS_METRIC

import javax.inject.Inject


class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val isMetric = sharedPreferences.getBoolean(IS_METRIC, false)

    fun setMetricImperialValue(isMetric: Boolean){
        val prefsEdit = sharedPreferences.edit()
        prefsEdit.putBoolean(IS_METRIC, isMetric)
        prefsEdit.apply()
    }


}