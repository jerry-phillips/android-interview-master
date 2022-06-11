package com.png.interview.weather.ui.binder


import com.png.interview.weather.ui.viewmodel.SettingsViewModel

const val IS_METRIC = "isMetric"

class SettingsFragmentViewBinder (
    private val viewModel: SettingsViewModel
        ) {

    val isMetric = viewModel.isMetric

    fun setUnitOfMeasure(isMetric: Boolean) {
        viewModel.setMetricImperialValue(isMetric)
    }

}