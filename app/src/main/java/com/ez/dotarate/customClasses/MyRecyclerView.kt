package com.ez.dotarate.customClasses

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerView : RecyclerView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("MyLogs", "RecyclerView. onTouchEvent")
        return false
    }

//    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//        //return super.onInterceptTouchEvent(e)
//        Log.d("MyLogs", "RecyclerView. onInterceptTouchEvent")
//        return false
//    }
}