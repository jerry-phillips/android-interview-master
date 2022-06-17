package com.png.interview.weather.domain


import android.content.SharedPreferences
import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.binder.IS_METRIC
import com.png.interview.weather.ui.model.ForeCastViewData
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import com.png.interview.weather.util.MetricUtil
import javax.inject.Inject


interface CreateWeatherForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String, days: Int): WeatherForecastViewRepresentation
}

class DefaultCreateWeatherForeCastRepFromQueryUseCase @Inject constructor(
    private val getWeatherForecastDataUseCase: GetWeatherForecastDataUseCase,
    private val sharedPreferences: SharedPreferences
) : CreateWeatherForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String, days: Int): WeatherForecastViewRepresentation {
        when (val result = getWeatherForecastDataUseCase(query, days)) {
            is NetworkResponse.Success -> {
                val isMetric = sharedPreferences.getBoolean(IS_METRIC, false)
                val list = result.body.forecast.forecastday.map {
                            ForeCastViewData(
                                date = it.date,
                                minTemp = MetricUtil.getMinTemp(isMetric, it.day),
                                maxTemp = MetricUtil.getMaxTemp(isMetric, it.day),
                                windSpeed = MetricUtil.getWindSpeed(isMetric, it.day),
                                condition = it.day.condition.text
                            )

                }
                return WeatherForecastViewRepresentation.WeatherForecastViewRep(list)
            }
            else -> {
                return WeatherForecastViewRepresentation.Error
            }
        }
    }
}