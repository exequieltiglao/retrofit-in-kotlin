package com.exequieltiglao.nothing.utils

import android.content.Intent

interface AndroidResultService {
    fun addActivityResultListener (listener: Listener)
    fun removeActivityResultListener (listener: Listener)

    interface Listener {
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}