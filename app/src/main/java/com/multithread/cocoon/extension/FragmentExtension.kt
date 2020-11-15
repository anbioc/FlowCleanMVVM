package com.multithread.cocoon.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


inline fun <T> Fragment.observeLiveData(
    liveData: LiveData<T>,
    crossinline onChanged: (data: T) -> Unit
) {
    liveData.observe(this, Observer {
        it?.let {
            onChanged(it)
        }
    })
}

fun Fragment.toastIt(message: String) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()
