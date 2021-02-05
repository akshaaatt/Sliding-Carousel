package com.alphelios.slider.indicator.draw.drawer.type

import android.graphics.Canvas
import android.graphics.Paint
import com.alphelios.slider.indicator.animation.data.Value
import com.alphelios.slider.indicator.animation.data.type.SlideAnimationValue
import com.alphelios.slider.indicator.draw.data.Indicator
import com.alphelios.slider.indicator.draw.data.Orientation

class SlideDrawer(paint: Paint, indicator: Indicator) : BaseDrawer(paint, indicator) {
    fun draw(
            canvas: Canvas,
            value: Value,
            coordinateX: Int,
            coordinateY: Int) {
        if (value !is SlideAnimationValue) {
            return
        }
        val coordinate = value.coordinate
        val unselectedColor = indicator.unselectedColor
        val selectedColor = indicator.selectedColor
        val radius = indicator.radius
        paint.color = unselectedColor
        canvas.drawCircle(coordinateX.toFloat(), coordinateY.toFloat(), radius.toFloat(), paint)
        paint.color = selectedColor
        if (indicator.orientation == Orientation.HORIZONTAL) {
            canvas.drawCircle(coordinate.toFloat(), coordinateY.toFloat(), radius.toFloat(), paint)
        } else {
            canvas.drawCircle(coordinateX.toFloat(), coordinate.toFloat(), radius.toFloat(), paint)
        }
    }
}