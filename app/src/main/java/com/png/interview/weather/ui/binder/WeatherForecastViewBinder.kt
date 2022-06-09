package com.png.interview.weather.ui.binder

import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel

class WeatherForecastViewBinder(
    private val viewModel: WeatherForecastViewModel,
    private val query: String
) {
    init {
        viewModel.getForecast(query, 3)
    }

    val forecastList = viewModel.forecastList
}