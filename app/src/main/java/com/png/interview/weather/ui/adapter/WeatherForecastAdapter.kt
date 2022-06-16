package com.png.interview.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ViewWeatherForecastBinding
import com.png.interview.weather.ui.model.ForeCastViewData

class WeatherForecastAdapter : RecyclerView.Adapter<WeatherForecastViewHolder>() {

    private var forecastList: List<ForeCastViewData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val binding = ViewWeatherForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        val item = forecastList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun updateList(items: List<ForeCastViewData>?) {
        forecastList = items ?: emptyList()
        notifyDataSetChanged()
    }

}