package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateWeatherForecastRepFromQueryUseCase
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val createWeatherForecastRepFromQueryUseCase: CreateWeatherForecastRepFromQueryUseCase,
) : ViewModel() {
    private val _weatherForecastViewRepresentation =
        MutableStateFlow<WeatherForecastViewRepresentation>(WeatherForecastViewRepresentation.EmptyList)

    fun getForecast(query: String, days: Int) {
        viewModelScope.launch {
            _weatherForecastViewRepresentation.value =
                createWeatherForecastRepFromQueryUseCase(query, days)
        }
    }

    val forecastList = _weatherForecastViewRepresentation
        .map { (it as? WeatherForecastViewRepresentation.WeatherForecastViewRep)?.dataList }
        .asLiveData()
}