package com.alphelios.slider.indicator.draw

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Pair
import android.view.MotionEvent
import com.alphelios.slider.indicator.animation.data.Value
import com.alphelios.slider.indicator.draw.controller.AttributeController
import com.alphelios.slider.indicator.draw.controller.DrawController
import com.alphelios.slider.indicator.draw.controller.DrawController.ClickListener
import com.alphelios.slider.indicator.draw.controller.MeasureController
import com.alphelios.slider.indicator.draw.data.Indicator

class DrawManager {
    private var indicator: Indicator = Indicator()
    private val drawController: DrawController
    private val measureController: MeasureController
    private val attributeController: AttributeController
    fun indicator(): Indicator {
        return indicator
    }

    fun setClickListener(listener: ClickListener?) {
        drawController.setClickListener(listener)
    }

    fun touch(event: MotionEvent?) {
        drawController.touch(event)
    }

    fun updateValue(value: Value?) {
        drawController.updateValue(value)
    }

    fun draw(canvas: Canvas) {
        drawController.draw(canvas)
    }

    fun measureViewSize(widthMeasureSpec: Int, heightMeasureSpec: Int): Pair<Int, Int> {
        return measureController.measureViewSize(indicator, widthMeasureSpec, heightMeasureSpec)
    }

    fun initAttributes(context: Context, attrs: AttributeSet?) {
        attributeController.init(context, attrs)
    }

    init {
        drawController = DrawController(indicator)
        measureController = MeasureController()
        attributeController = AttributeController(indicator)
    }
}