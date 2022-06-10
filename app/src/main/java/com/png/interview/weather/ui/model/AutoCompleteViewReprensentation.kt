package com.png.interview.weather.ui.model

sealed class AutoCompleteViewReprensentation {
    class AutoCompleteViewRep(val dataList: List<String>): AutoCompleteViewReprensentation()
    object EmptyList: AutoCompleteViewReprensentation()
    object Error: AutoCompleteViewReprensentation()
}
