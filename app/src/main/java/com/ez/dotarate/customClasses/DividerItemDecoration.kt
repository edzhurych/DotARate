package com.ez.dotarate.customClasses

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R


class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable = context.getDrawable(R.drawable.divider)!!

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)

            if ((parent.getChildAdapterPosition(child) == parent.adapter!!.itemCount - 1) && parent.bottom < bottom) { // this prevent a parent to hide the last item's divider
                parent.setPadding(
                    parent.paddingLeft,
                    parent.paddingTop,
                    parent.paddingRight,
                    bottom - parent.bottom
                )
            }

            mDivider.draw(c)
        }
    }
}