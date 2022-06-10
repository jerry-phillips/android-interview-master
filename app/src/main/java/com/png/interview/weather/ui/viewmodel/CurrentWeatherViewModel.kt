package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateAutoCompleteRepFromQueryUseCase
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutoCompleteViewReprensentation
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutoCompleteRepFromQueryUseCase: CreateAutoCompleteRepFromQueryUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autoCompleteViewRepresentation = MutableStateFlow<AutoCompleteViewReprensentation>(AutoCompleteViewReprensentation.EmptyList)

    fun submitCurrentWeatherSearch(query: String) {
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query)
        }
    }

    fun getTextSuggestions(query: String) {
        viewModelScope.launch {
            _autoCompleteViewRepresentation.value = createAutoCompleteRepFromQueryUseCase(query)
        }
    }

    val autoCompleteTerms =
        _autoCompleteViewRepresentation
            .map { (it as? AutoCompleteViewReprensentation.AutoCompleteViewRep)?.dataList }
            .asLiveData()

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map { (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data }
            .asLiveData()

    val isEmptyVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Empty }
            .asLiveData()

    val isErrorVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Error }
            .asLiveData()
}