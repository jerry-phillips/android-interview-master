package com.png.interview.weather.domain

import android.content.SharedPreferences
import com.png.interview.api.common_model.NetworkResponse
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
                CurrentWeatherViewRepresentation.AvailableWeatherViewRep(
                    AvailableWeatherViewData(
                        name = result.body.location.name,
                        date = result.body.location.localtime,
                        temperature = "${result.body.current.temp_f} ${MetricUtil(sharedPreferences).temperature}",
                        condition = result.body.current.condition.text,
                        windDirection = result.body.current.wind_dir,
                        windSpeed = "${result.body.current.gust_mph} ${MetricUtil(sharedPreferences).speed}"
                    )
                )
            }
            else -> {
                CurrentWeatherViewRepresentation.Error
            }
        }
    }
}