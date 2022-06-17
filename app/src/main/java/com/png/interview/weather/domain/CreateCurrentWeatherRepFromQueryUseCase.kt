package com.png.interview.weather.domain

import android.content.SharedPreferences
import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.binder.IS_METRIC
import com.png.interview.weather.ui.model.AvailableWeatherViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import com.png.interview.weather.util.MetricUtil
import javax.inject.Inject

interface CreateCurrentWeatherRepFromQueryUseCase {
    suspend operator fun invoke(query: String): CurrentWeatherViewRepresentation
}

class DefaultCreateCurrentWeatherRepFromQueryUseCase @Inject constructor(
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase,
    private val sharedPreferences: SharedPreferences
) : CreateCurrentWeatherRepFromQueryUseCase {
    override suspend fun invoke(query: String): CurrentWeatherViewRepresentation {
        return when (val result = getCurrentWeatherDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val isMetric = sharedPreferences.getBoolean(IS_METRIC, false)
                CurrentWeatherViewRepresentation.AvailableWeatherViewRep(
                    AvailableWeatherViewData(
                        name = result.body.location.name,
                        date = result.body.location.localtime,
                        temperature = MetricUtil.getCurrentTemp(isMetric, result.body.current),
                        condition = result.body.current.condition.text,
                        windDirection = result.body.current.wind_dir,
                        windSpeed = MetricUtil.getCurrentSpeed(isMetric, result.body.current)
                    )
                )
            }
            else -> {
                CurrentWeatherViewRepresentation.Error
            }
        }
    }
}