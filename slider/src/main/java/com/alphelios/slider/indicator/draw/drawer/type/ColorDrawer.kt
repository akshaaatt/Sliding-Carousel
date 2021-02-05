package com.alphelios.slider.indicator.draw.drawer.type

import android.graphics.Canvas
import android.graphics.Paint
import com.alphelios.slider.indicator.animation.data.Value
import com.alphelios.slider.indicator.animation.data.type.ColorAnimationValue
import com.alphelios.slider.indicator.draw.data.Indicator

class ColorDrawer(paint: Paint, indicator: Indicator) : BaseDrawer(paint, indicator) {
    fun draw(canvas: Canvas,
             value: Value,
             position: Int,
             coordinateX: Int,
             coordinateY: Int) {
        if (value !is ColorAnimationValue) {
            return
        }
        val v = value
        val radius = indicator.radius.toFloat()
        var color = indicator.selectedColor
        val selectedPosition = indicator.selectedPosition
        val selectingPosition = indicator.selectingPosition
        val lastSelectedPosition = indicator.lastSelectedPosition
        if (indicator.isInteractiveAnimation) {
            if (position == selectingPosition) {
                color = v.color
            } else if (position == selectedPosition) {
                color = v.colorReverse
            }
        } else {
            if (position == selectedPosition) {
                color = v.color
            } else if (position == lastSelectedPosition) {
                color = v.colorReverse
            }
        }
        paint.color = color
        canvas.drawCircle(coordinateX.toFloat(), coordinateY.toFloat(), radius, paint)
    }
}