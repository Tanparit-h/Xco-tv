package com.zicure.xcotv.utils

import androidx.lifecycle.Observer

open class Event <out T>(private val content: T) {
    var hasBeenHandle = false
        private set // Allow external read but not write

    fun getContentIfNotHandle(): T? {
        return if (hasBeenHandle) {
            null
        } else {
            hasBeenHandle = true
            content
        }
    }

    fun peekContent(): T = content
}

open class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>) {
        event?.getContentIfNotHandle()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}