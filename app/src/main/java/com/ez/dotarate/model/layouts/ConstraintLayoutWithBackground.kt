package com.ez.dotarate.model.layouts

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ez.dotarate.R
import com.ez.dotarate.ViewUtils.generateBackgroundWithShadow
import com.ez.dotarate.constants.HIDE_NONE

class ConstraintLayoutWithBackground : ConstraintLayout {

    constructor(context: Context) : super(context) {
        initBackground()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        initBackground()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initBackground()
    }

    private fun initBackground() {
        background = generateBackgroundWithShadow(
            view = this,
            backgroundColor = R.color.whiteLight,
            cornerRadius = R.dimen.radiusCorner,
            shadowColor = R.color.colorShadow,
            elevation = R.dimen.elevation,
            shadowGravity = Gravity.CENTER,
            side = HIDE_NONE
        )
    }
}