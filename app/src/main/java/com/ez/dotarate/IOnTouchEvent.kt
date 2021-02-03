package com.ez.dotarate

import android.view.MotionEvent

interface IOnTouchEvent {
    fun onTouchEvent(event: MotionEvent?): Boolean
}