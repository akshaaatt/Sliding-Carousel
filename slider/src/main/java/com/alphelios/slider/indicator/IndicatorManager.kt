package com.alphelios.slider.indicator

import com.alphelios.slider.indicator.animation.AnimationManager
import com.alphelios.slider.indicator.animation.controller.ValueController.UpdateListener
import com.alphelios.slider.indicator.animation.data.Value
import com.alphelios.slider.indicator.draw.DrawManager
import com.alphelios.slider.indicator.draw.data.Indicator

class IndicatorManager internal constructor(private val listener: Listener?) : UpdateListener {
    private val drawManager: DrawManager = DrawManager()
    private val animationManager: AnimationManager

    internal interface Listener {
        fun onIndicatorUpdated()
    }

    fun animate(): AnimationManager {
        return animationManager
    }

    fun indicator(): Indicator {
        return drawManager.indicator()
    }

    fun drawer(): DrawManager {
        return drawManager
    }

    override fun onValueUpdated(value: Value?) {
        drawManager.updateValue(value)
        listener?.onIndicatorUpdated()
    }

    init {
        animationManager = AnimationManager(drawManager.indicator(), this)
    }
}