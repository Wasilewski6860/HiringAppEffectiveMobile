package ru.hiringapp.base_feature.extensions

import android.os.Handler
import android.os.Looper
import android.view.View

const val DEFAULT_DEBOUNCE_INTERVAL = 400L

inline fun View.setOnDebounceClickListener(
    crossinline listener: (View) -> Unit = {},
) {
    var isDebouncing = false
    val handler = Handler(Looper.getMainLooper())
    val runnable = Runnable { isDebouncing = false }

    setOnClickListener {
        if (isDebouncing) return@setOnClickListener
        isDebouncing = true
        handler.postDelayed(runnable, DEFAULT_DEBOUNCE_INTERVAL)
        listener(it)
    }
}