package com.png.interview.weather.ui.binder

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.png.interview.R
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: (query: String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val autoCompleteTerms = viewModel.autoCompleteTerms
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    var input: String = ""
    private var currentInput: String = ""

    val textChangeListener = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            if (editable != null && editable.length > 2) {
                viewModel.getTextSuggestions(editable.toString())
            }
        }

    }

    val selectedListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
        input = adapter.getItemAtPosition(pos) as String
        goClicked()
    }


    fun refreshClicked() {
        if (currentInput.isNotEmpty()) {
            viewModel.submitCurrentWeatherSearch(currentInput)
        }
    }

    fun seeForecastClicked() {
        if (currentInput.isNotEmpty()) {
            forecastAction(currentInput)
        }
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
        } else {
            viewModel.submitCurrentWeatherSearch(input)
            currentInput = input
        }
    }
}

@BindingAdapter("entries")
fun bindAutocomplete(textView: AutoCompleteTextView, terms: List<String>?){
    terms?.let {
        val adapter = ArrayAdapter(
            textView.context,
            R.layout.support_simple_spinner_dropdown_item,
            it)

        textView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }
}
