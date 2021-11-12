package com.pallaw.servicesandbackgroundtasks.util

import android.widget.TextView
import timber.log.Timber

object TextLogger {
    fun log(textView: TextView, logText: String) {
        val previousText = textView.text
        if (previousText.isEmpty()) {
            textView.text = String.format(logText)
        } else {
            textView.text = String.format("$previousText \n$logText")
        }

        Timber.tag("").d(logText)
    }
}
