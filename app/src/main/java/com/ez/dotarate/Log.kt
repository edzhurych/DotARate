package com.ez.dotarate

import android.util.Log

object Log {
    private const val tag ="MyLogs"

    fun d(message: String) {
        Log.d(tag, message)
    }
}