package com.png.interview.weather.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ViewWeatherForecastBinding
import com.png.interview.weather.ui.model.ForeCastViewData

class WeatherForecastViewHolder(private val binding: ViewWeatherForecastBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecastWeatherViewData: ForeCastViewData) {
        binding.forecastViewData = forecastWeatherViewData
    }
}