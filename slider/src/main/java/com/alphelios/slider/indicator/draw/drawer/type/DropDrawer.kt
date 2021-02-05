package com.alphelios.slider.indicator.draw.drawer.type

import android.graphics.Canvas
import android.graphics.Paint
import com.alphelios.slider.indicator.animation.data.Value
import com.alphelios.slider.indicator.animation.data.type.DropAnimationValue
import com.alphelios.slider.indicator.draw.data.Indicator
import com.alphelios.slider.indicator.draw.data.Orientation

class DropDrawer(paint: Paint, indicator: Indicator) : BaseDrawer(paint, indicator) {
    fun draw(
            canvas: Canvas,
            value: Value,
            coordinateX: Int,
            coordinateY: Int) {
        if (value !is DropAnimationValue) {
            return
        }
        val v = value
        val unselectedColor = indicator.unselectedColor
        val selectedColor = indicator.selectedColor
        val radius = indicator.radius.toFloat()
        paint.color = unselectedColor
        canvas.drawCircle(coordinateX.toFloat(), coordinateY.toFloat(), radius, paint)
        paint.color = selectedColor
        if (indicator.orientation == Orientation.HORIZONTAL) {
            canvas.drawCircle(v.width.toFloat(), v.height.toFloat(), v.radius.toFloat(), paint)
        } else {
            canvas.drawCircle(v.height.toFloat(), v.width.toFloat(), v.radius.toFloat(), paint)
        }
    }
}