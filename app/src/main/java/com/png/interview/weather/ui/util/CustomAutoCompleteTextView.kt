package com.png.interview.weather.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.AutoCompleteTextView


@SuppressLint("AppCompatCustomView")
class CustomAutoCompleteTextView : AutoCompleteTextView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    override fun replaceText(text: CharSequence) {
        this.setText("")
    }
}