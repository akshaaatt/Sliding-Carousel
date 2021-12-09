@file:JvmName("Utils")

package com.aemerse.slider.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.aemerse.slider.model.CarouselItem

/**
 * This method converts device specific pixels to density independent pixels.
 */
fun Int.pxToDp(context: Context): Int {
    return (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 */
fun Int.dpToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

/**
 * This method converts sp unit to equivalent pixels, depending on device density.
 */
fun Int.spToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

/**
 * Get current snap item position of a recyclerView.
 *
 * @param layoutManager Target recyclerView
 * @return Position of the item or RecyclerView.NO_POSITION (-1)
 */
fun SnapHelper.getSnapPosition(layoutManager: RecyclerView.LayoutManager?): Int {
    if (layoutManager == null) {
        return RecyclerView.NO_POSITION
    }
    val snapView: View = this.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

/**
 * Set image to the carouse image view.
 *
 * @param item The carousel item.
 */
fun ImageView.setImage(item: CarouselItem) {
    this.setImage(
        item = item,
        placeholderDrawableResourceId = null,
    )
}

/**
 * Set image to the carouse image view.
 *
 * @param item The carousel item.
 * @param placeholderDrawableResourceId The id of the resource to use as a placeholder.
 */
private fun ImageView.setImage(
    item: CarouselItem,
    @DrawableRes placeholderDrawableResourceId: Int? = null
) {
    val glide = Glide.with(context.applicationContext)

    var requestBuilder = when {
        item.imageUrl != null && item.headers == null -> {
            glide.load(item.imageUrl)
        }
        item.headers != null -> {
            glide.load(GlideUrl(item.imageUrl.toString()) { item.headers })
        }
        else -> {
            glide.load(item.imageDrawable)
        }
    }

    requestBuilder = when {
        placeholderDrawableResourceId != null -> {
            requestBuilder.placeholder(placeholderDrawableResourceId)
        }
        else -> requestBuilder
    }

    requestBuilder.into(this)
}
