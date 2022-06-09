package com.png.interview.weather.ui.binder

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.weather.ui.adapter.WeatherForeCastAdapter
import com.png.interview.weather.ui.model.ForeCastViewData
import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel

class WeatherForecastViewBinder(
    viewModel: WeatherForecastViewModel,
    query: String
) {
    init {
        viewModel.getForecast(query, 3)
    }

    val forecastList = viewModel.forecastList

}

@BindingAdapter("forecastList")
fun bindDataList(recyclerView: RecyclerView, forecastList: List<ForeCastViewData>?) {
    val adapter = WeatherForeCastAdapter()
    recyclerView.adapter = adapter
    adapter.updateList(forecastList)
}