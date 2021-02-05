package com.alphelios.demo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alphelios.slider.indicator.animation.type.IndicatorAnimationType
import com.alphelios.slider.indicator.draw.controller.DrawController.ClickListener
import com.alphelios.slider.SliderAnimations
import com.alphelios.slider.SliderView
import com.alphelios.demo.models.SliderItem

class MainActivity : AppCompatActivity() {
    lateinit var sliderView: SliderView
    private var adapter: SliderAdapterExample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderView = findViewById(R.id.imageSlider)
        adapter = SliderAdapterExample(this)
        sliderView.setSliderAdapter(adapter!!)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()
        sliderView.setOnIndicatorClickListener(object : ClickListener {
            override fun onIndicatorClicked(position: Int) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.currentPagePosition)
            }
        })

        val sliderItem = SliderItem()
        sliderItem.description = "Slider Item"
        sliderItem.imageUrl = "https://images.unsplash.com/photo-1585424508807-02efe2b01888?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop"
        adapter!!.addItem(sliderItem)
        sliderItem.imageUrl = "https://images.unsplash.com/photo-1543716091-a840c05249ec?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop"
        adapter!!.addItem(sliderItem)
        sliderItem.imageUrl = "https://images.unsplash.com/photo-1517811409552-396f829138a2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop"
        adapter!!.addItem(sliderItem)
    }
}