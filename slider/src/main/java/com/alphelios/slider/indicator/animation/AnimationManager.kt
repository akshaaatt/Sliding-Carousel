package com.alphelios.slider.indicator.animation

import com.alphelios.slider.indicator.animation.controller.AnimationController
import com.alphelios.slider.indicator.animation.controller.ValueController.UpdateListener
import com.alphelios.slider.indicator.draw.data.Indicator

class AnimationManager(indicator: Indicator, listener: UpdateListener) {
    private val animationController: AnimationController?
    fun basic() {
        if (animationController != null) {
            animationController.end()
            animationController.basic()
        }
    }

    fun interactive(progress: Float) {
        animationController?.interactive(progress)
    }

    fun end() {
        animationController?.end()
    }

    init {
        animationController = AnimationController(indicator, listener)
    }
}