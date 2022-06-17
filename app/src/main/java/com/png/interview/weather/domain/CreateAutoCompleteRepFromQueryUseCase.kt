package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AutoCompleteViewReprensentation
import javax.inject.Inject

interface CreateAutoCompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String): AutoCompleteViewReprensentation
}

class DefaultCreateAutoCompleteRepFromQueryUseCase @Inject constructor(
    private val getAutoCompleteDataUseCase: GetAutoCompleteDataUseCase
) : CreateAutoCompleteRepFromQueryUseCase {
    override suspend fun invoke(query: String): AutoCompleteViewReprensentation {
        return when (val result = getAutoCompleteDataUseCase(query)) {
            is NetworkResponse.Success -> {
                AutoCompleteViewReprensentation.AutoCompleteViewRep(result.body.take(5).map {
                    "${it.name}, ${it.region}, ${it.country}"
                })
            }
            else -> {
                AutoCompleteViewReprensentation.Error
            }
        }
    }
}