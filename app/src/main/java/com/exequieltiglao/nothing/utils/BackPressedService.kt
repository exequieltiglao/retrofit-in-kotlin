package com.exequieltiglao.nothing.utils

import android.content.Intent

interface BackPressedService {
    fun addBackPressedListener (listener: Listener)
    fun removeBackPressedListener (listener: Listener)

    interface Listener {
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}