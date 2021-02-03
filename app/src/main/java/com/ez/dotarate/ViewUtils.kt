package com.ez.dotarate

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.Gravity
import android.view.View
import android.view.View.LAYER_TYPE_SOFTWARE
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.ez.dotarate.constants.HIDE_END
import com.ez.dotarate.constants.HIDE_START


object ViewUtils {

    /**
     * Функция, которая устанавливает фон для View
     * @param view
     *  Обычно является макетом(layout)
     *
     * @param backgroundColor
     * @ColorRes
     *  Нужно передать цвет, который будет фоном для view
     *
     * @param cornerRadius
     * @DimenRes
     *  Нужно передать размер, который установит радиус углов
     *
     * @param shadowColor
     * @ColorRes
     *  Нужно передать цвет для тени
     *
     * @param elevation
     * @DimenRes
     *  Нужно передать размер, который устанавливает отступы
     *
     * @param shadowGravity
     *  Нужно передать "сторону", в которую требуется сместить тень
     *
     * @param side
     *  Нужно передать какую сторону прямоугольника не отображать
     */
    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")
    fun generateBackgroundWithShadow(
        view: View, @ColorRes backgroundColor: Int,
        @DimenRes cornerRadius: Int,
        @ColorRes shadowColor: Int,
        @DimenRes elevation: Int,
        shadowGravity: Int,
        side: Int
    ): Drawable {
        val cornerRadiusValue = view.context.resources.getDimension(cornerRadius)
        val elevationValue = view.context.resources.getDimension(elevation).toInt()
        val shadowColorValue = ContextCompat.getColor(view.context, shadowColor)
        val backgroundColorValue = ContextCompat.getColor(view.context, backgroundColor)

        // Список из восьми одинаковых значений параметра cornerRadius
        val outerRadius = floatArrayOf(
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue,
            cornerRadiusValue
        )

//        val backgroundPaint = Paint()
//        backgroundPaint.style = Paint.Style.FILL
//        backgroundPaint.setShadowLayer(cornerRadiusValue, 0F, 0F, 0)

        // Создаем прямоугольник
        val shapeDrawablePadding = Rect()
        // Устанавливаем ему внутренние отступы слева и справа
        shapeDrawablePadding.left = elevationValue
        shapeDrawablePadding.right = elevationValue

        // Перменная, которая регулирует соотношение по оси Y
        val dY: Float
        // В зависимости от требуемой "стороны" тени устанавливаем внутренние отступы сверху и снизу. Смещает тень
        when (shadowGravity) {
            Gravity.CENTER -> {
                shapeDrawablePadding.top = 0
                shapeDrawablePadding.bottom = 0
                dY = 0F
            }
            Gravity.TOP -> {
                shapeDrawablePadding.top = elevationValue * 2
                shapeDrawablePadding.bottom = elevationValue
                dY = -1 * elevationValue / 3F
            }
            Gravity.BOTTOM -> {
                shapeDrawablePadding.top = elevationValue
                shapeDrawablePadding.bottom = elevationValue
                dY = elevationValue / 3F
            }
            Gravity.END -> {
                shapeDrawablePadding.top = elevationValue
                shapeDrawablePadding.bottom = elevationValue
                dY = 0F
            }
            else -> {
                shapeDrawablePadding.top = elevationValue
                shapeDrawablePadding.bottom = elevationValue * 2
                dY = elevationValue / 3F
            }
        }

        // Создаем фигуру и передаем ей приямоугольник с отступами
        val shapeDrawable = ShapeDrawable()
        shapeDrawable.setPadding(shapeDrawablePadding)

        // Рисуем цвет фона
        shapeDrawable.paint.color = backgroundColorValue

        // Рисуем тень
        shapeDrawable.paint.setShadowLayer(
            // Размер тени
            2F,
            // Смещение относительно оси Х
            0F,
            // Смещение относительно оси Y
            dY,
            // Цвет тени
            shadowColorValue
        )

        // Рисуем фигуру на view
        view.setLayerType(LAYER_TYPE_SOFTWARE, shapeDrawable.paint)

        // Устанавливаем углы для фигуры
        shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

        // Устанавливаем внешние отступы
        val drawable = LayerDrawable(arrayOf<Drawable>(shapeDrawable))
        when (side) {
            HIDE_START -> {
                drawable.setLayerInset(
                    0,
                    0,
                    elevationValue * 2,
                    elevationValue,
                    elevationValue * 2
                )
            }
            HIDE_END -> {
            drawable.setLayerInset(
                0,
                elevationValue,
                elevationValue * 2,
                0,
                elevationValue * 2
            )
        }
            else -> {drawable.setLayerInset(
                0,
                elevationValue,
                elevationValue * 2,
                elevationValue,
                elevationValue * 2
            )}
        }

        return drawable
    }
}