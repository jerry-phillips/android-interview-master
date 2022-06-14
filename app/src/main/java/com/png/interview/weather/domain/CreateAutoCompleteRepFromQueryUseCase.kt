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
                val items = mutableListOf<String>()
                val size = if (result.body.size <= 5) result.body.size else 5
                for (i in 0 until size) {
                    var value =
                            "${result.body[i].name}, ${result.body[i].region}, ${result.body[i].country}"

                    items.add(value)
                }
                AutoCompleteViewReprensentation.AutoCompleteViewRep(items)
            }
            else -> {
                AutoCompleteViewReprensentation.Error
            }
        }
    }
}