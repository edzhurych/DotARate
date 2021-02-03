package com.ez.dotarate.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView


class RecyclerTouchListener(
    context: Context,
    recyclerView: RecyclerView?,
    private val clickListener: ClickListener?
) : RecyclerView.OnItemTouchListener {

    private val gestureDetector: GestureDetector

    init {
        gestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    if (recyclerView != null) {
                        val child = recyclerView.findChildViewUnder(e.x, e.y)
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(
                                child,
                                recyclerView.getChildAdapterPosition(child)
                            )
                        }
                    }
                }
            })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

        val child = rv.findChildViewUnder(e.x, e.y)
        if (child != null && gestureDetector.onTouchEvent(e)) {
            clickListener!!.onClick(child, rv.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}