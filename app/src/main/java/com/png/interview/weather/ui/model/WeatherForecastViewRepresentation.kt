package com.png.interview.weather.ui.model

sealed class WeatherForecastViewRepresentation {
    class WeatherForecastViewRep(val dataList: List<ForeCastViewData>) : WeatherForecastViewRepresentation()
    object EmptyList : WeatherForecastViewRepresentation()
    object Error : WeatherForecastViewRepresentation()
}

