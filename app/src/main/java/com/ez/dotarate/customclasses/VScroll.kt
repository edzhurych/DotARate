package com.ez.dotarate.customclasses

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.ez.dotarate.Log


class VScroll : ScrollView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("VScroll onTouchEvent")
        when(event.action) {
            MotionEvent.ACTION_BUTTON_PRESS -> {
                performClick()
            }
        }
        return false
    }

    override fun performClick(): Boolean {
        Log.d("VScroll performClick")
        return super.performClick()
    }

    fun onTouchListener(event: MotionEvent) {
        super.onTouchEvent(event)
    }
}