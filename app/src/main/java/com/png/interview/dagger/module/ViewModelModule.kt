package com.png.interview.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.png.interview.dagger.viewmodel.ViewModelFactory
import com.png.interview.dagger.viewmodel.ViewModelKey
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import com.png.interview.weather.ui.viewmodel.SettingsViewModel
import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    internal abstract fun currentWeatherViewModel(viewModel: CurrentWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastViewModel::class)
    internal abstract fun weatherForecastViewModel(viewModel: WeatherForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    internal abstract fun settingsViewModel(viewModel: SettingsViewModel): ViewModel


}
