package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.AutcompleteResponseItem
import javax.inject.Inject


interface GetAutoCompleteDataUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<List<AutcompleteResponseItem>, Unit>
}

class DefaultGetAutoCompleteRepDataUseCase @Inject constructor(
    private val weatherApi: WeatherApi
) : GetAutoCompleteDataUseCase {
    override suspend fun invoke(query: String): NetworkResponse<List<AutcompleteResponseItem>, Unit> {
        return weatherApi.getAutocompleteResults(query)
    }
}