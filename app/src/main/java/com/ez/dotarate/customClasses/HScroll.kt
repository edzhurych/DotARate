package com.ez.dotarate.customClasses

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.HorizontalScrollView


class HScroll : HorizontalScrollView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return false
    }

    fun onTouchListener(event: MotionEvent) {
        super.onTouchEvent(event)
    }
}