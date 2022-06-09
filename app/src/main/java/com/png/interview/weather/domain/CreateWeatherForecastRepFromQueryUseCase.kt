package com.png.interview.weather.domain


import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.ForeCastViewData
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import javax.inject.Inject

interface CreateWeatherForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String, days: Int): WeatherForecastViewRepresentation
}

class DefaultCreateWeatherForeCastRepFromQueryUseCase @Inject constructor(
    private val getWeatherForecastDataUseCase: GetWeatherForecastDataUseCase
) : CreateWeatherForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String, days: Int): WeatherForecastViewRepresentation {
        return when (val result = getWeatherForecastDataUseCase(query, days)) {
            is NetworkResponse.Success -> {
                val forecastList = mutableListOf<ForeCastViewData>()
                result.body.forecast.forecastday.forEach {forecastDay ->
                    forecastList.add(
                        ForeCastViewData(
                            date = forecastDay.date ,
                            minTemp = "${forecastDay.day.mintemp_f} F",
                            maxTemp = "${forecastDay.day.maxtemp_f} F",
                            windSpeed = "${forecastDay.day.maxwind_mph} MPH",
                            condition = forecastDay.day.condition.text
                        )
                    )
                }
                WeatherForecastViewRepresentation.WeatherForecastViewRep(forecastList)
            }
            else -> {
                WeatherForecastViewRepresentation.Error
            }
        }
    }
}