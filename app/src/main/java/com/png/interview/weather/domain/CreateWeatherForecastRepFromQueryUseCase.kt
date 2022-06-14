package com.png.interview.weather.domain


import android.content.SharedPreferences
import com.png.interview.api.common_model.NetworkResponse
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
                val forecastList = mutableListOf<ForeCastViewData>()
                val metricUtil = MetricUtil(sharedPreferences)
                result.body.forecast.forecastday.forEach { forecastDay ->
                    forecastList.add(
                        ForeCastViewData(
                            date = forecastDay.date,
                            minTemp = metricUtil.getMinTemp(forecastDay.day),
                            maxTemp = metricUtil.getMaxTemp(forecastDay.day),
                            windSpeed = metricUtil.getWindSpeed(forecastDay.day),
                            condition = forecastDay.day.condition.text
                        )
                    )
                }
                return WeatherForecastViewRepresentation.WeatherForecastViewRep(forecastList)
            }
            else -> {
                return WeatherForecastViewRepresentation.Error
            }
        }
    }
}