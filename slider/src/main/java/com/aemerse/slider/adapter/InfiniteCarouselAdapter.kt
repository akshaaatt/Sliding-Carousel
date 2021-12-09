package com.aemerse.slider.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aemerse.slider.model.CarouselGravity
import com.aemerse.slider.model.CarouselItem
import com.aemerse.slider.model.CarouselType

class InfiniteCarouselAdapter(
    recyclerView: RecyclerView,
    carouselType: CarouselType,
    carouselGravity: CarouselGravity,
    autoWidthFixing: Boolean,
    imageScaleType: ImageView.ScaleType,
) : FiniteCarouselAdapter(
    recyclerView,
    carouselType,
    carouselGravity,
    autoWidthFixing,
    imageScaleType
) {
    override fun getItemCount(): Int {
        return if (dataList.isEmpty()) 0 else Integer.MAX_VALUE
    }

    override fun getItem(position: Int): CarouselItem? {
        return if (position < itemCount) {
            dataList[position % dataList.size]
        } else {
            null
        }
    }

    override fun getRealDataPosition(position: Int): Int {
        return position % dataList.size
    }
}
