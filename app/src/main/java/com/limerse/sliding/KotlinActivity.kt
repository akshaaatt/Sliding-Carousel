package com.limerse.sliding

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.limerse.slider.listener.CarouselListener
import com.limerse.slider.listener.CarouselOnScrollListener
import com.limerse.slider.model.CarouselGravity
import com.limerse.slider.model.CarouselItem
import com.limerse.slider.model.CarouselType
import com.limerse.slider.utils.dpToPx
import com.limerse.slider.utils.setImage
import com.limerse.slider.utils.spToPx
import com.limerse.sliding.databinding.*
import kotlin.random.Random

class KotlinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKotlinBinding

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        // ----------------------------------------------------------------
        // Example One: All methods/attributes & header
        // ----------------------------------------------------------------

        binding.carousel1.apply {
            registerLifecycle(lifecycle)

            showTopShadow = true
            topShadowAlpha = 0.15f // 0 to 1, 1 means 100%
            topShadowHeight = 32.dpToPx(context) // px value of dp

            showBottomShadow = true
            bottomShadowAlpha = 0.6f // 0 to 1, 1 means 100%
            bottomShadowHeight = 64.dpToPx(context) // px value of dp

            showCaption = true
            captionMargin = 0.dpToPx(context) // px value of dp
            captionTextSize = 14.spToPx(context) // px value of sp

            showIndicator = true
            indicatorMargin = 0.dpToPx(context) // px value of dp

            imageScaleType = ImageView.ScaleType.CENTER_CROP

            carouselBackground = ColorDrawable(Color.parseColor("#333333"))

            carouselPadding = 0.dpToPx(context)
            carouselPaddingStart = 0.dpToPx(context)
            carouselPaddingTop = 0.dpToPx(context)
            carouselPaddingEnd = 0.dpToPx(context)
            carouselPaddingBottom = 0.dpToPx(context)

            showNavigationButtons = true
            previousButtonLayout = R.layout.previous_button_layout
            previousButtonId = R.id.btn_previous
            previousButtonMargin = 4.dpToPx(context) // px value of dp
            nextButtonLayout = R.layout.next_button_layout
            nextButtonId = R.id.btn_next
            nextButtonMargin = 4.dpToPx(context) // px value of dp

            carouselType = CarouselType.BLOCK

            carouselGravity = CarouselGravity.CENTER

            scaleOnScroll = false
            scalingFactor = .15f // 0 to 1; 1 means 100
            autoWidthFixing = true
            autoPlay = true
            autoPlayDelay = 3000 // Milliseconds
            infiniteCarousel = true
            touchToPause = true

            carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    val intent = Intent(this@KotlinActivity, SelectedImage::class.java)
                    intent.putExtra("file",carouselItem.imageUrl ?: carouselItem.imageDrawable )
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@KotlinActivity).toBundle())
                }

                override fun onLongClick(position: Int, carouselItem: CarouselItem) {
                    Toast.makeText(
                        this@KotlinActivity,
                        "You long clicked it.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            // Dummy header
            val headers = mutableMapOf<String, String>()
            headers["header_key"] = "header_value"

            val listOne = mutableListOf<CarouselItem>()

            for (item in DataSet.five) {
                listOne.add(
                    CarouselItem(
                        imageDrawable = item.first,
                        caption = item.second,
                    )
                )
            }

            binding.carousel1.setData(listOne)

            // ----------------------------------------------------------------
            // Example Two: Custom view
            // ----------------------------------------------------------------

            binding.carousel2.registerLifecycle(lifecycle)

            // Custom view
            binding.carousel2.carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemCustomFixedSizeLayout5Binding.inflate(layoutInflater, parent, false)
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemCustomFixedSizeLayout5Binding

                    currentBinding.imageView.apply {
                        scaleType = imageScaleType
                        setImage(item)
                    }
                }
            }
            val listTwo = mutableListOf<CarouselItem>()

            for (item in DataSet.two) {
                listTwo.add(
                    CarouselItem(
                        imageUrl = item
                    )
                )
            }

            binding.carousel2.setData(listTwo)

            // ----------------------------------------------------------------
            // Example Three: Custom navigation
            // ----------------------------------------------------------------

            binding.carousel3.registerLifecycle(lifecycle)

            // Custom view
            binding.carousel3.carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemCustomFixedSizeLayout1Binding.inflate(layoutInflater, parent, false)
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemCustomFixedSizeLayout1Binding

                    currentBinding.imageView.apply {
                        scaleType = imageScaleType

                        // carousel_default_placeholder is the default placeholder comes with
                        // the library.
                        setImage(item)
                    }
                }
            }

            val listThree = mutableListOf<CarouselItem>()

            for (item in DataSet.three) {
                listThree.add(
                    CarouselItem(
                        imageUrl = item.first,
                        caption = item.second
                    )
                )
            }

            binding.carousel3.setData(listThree)

            binding.customCaption.isSelected = true

            binding.carousel3.onScrollListener = object : CarouselOnScrollListener {

                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int,
                    position: Int,
                    carouselItem: CarouselItem?
                ) {

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        carouselItem?.apply {
                            binding.customCaption.text = caption
                        }
                    }
                }

                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                    position: Int,
                    carouselItem: CarouselItem?
                ) {
                    // ...
                }
            }

            // Custom navigation
            binding.btnGotoPrevious.setOnClickListener {
                binding.carousel3.previous()
            }

            binding.btnGotoNext.setOnClickListener {
                binding.carousel3.next()
            }

            // ----------------------------------------------------------------
            // Example Four: Custom indicator
            // ----------------------------------------------------------------

            binding.carousel4.registerLifecycle(lifecycle)

            // Custom view
            binding.carousel4.carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemCustomFixedSizeLayout3Binding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemCustomFixedSizeLayout3Binding

                    currentBinding.imageView.apply {
                        scaleType = imageScaleType

                        setImage(item)
                    }
                }
            }

            val listFour = mutableListOf<CarouselItem>()

            for (item in DataSet.four) {
                listFour.add(
                    CarouselItem(
                        imageUrl = item
                    )
                )
            }

            binding.carousel4.setData(listFour)

            // Custom indicator
            binding.carousel4.setIndicator(binding.customIndicator)

            // ----------------------------------------------------------------
            // Example Five: Usage of Drawable
            // ----------------------------------------------------------------

            binding.carousel5.registerLifecycle(lifecycle)

            // Custom view
            binding.carousel5.carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemCustomFixedSizeLayout2Binding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemCustomFixedSizeLayout2Binding

                    currentBinding.textView.text = item.caption ?: ""

                    currentBinding.imageView.apply {
                        scaleType = imageScaleType

                        setImage(item)
                    }

                    currentBinding.tvRating.text =
                        "%.1f (%d)".format(Random.nextDouble(4.0, 5.0), Random.nextInt(500))
                }
            }

            val listFive = mutableListOf<CarouselItem>()

            for (item in DataSet.five) {
                listFive.add(
                    CarouselItem(
                        imageDrawable = item.first,
                        caption = item.second
                    )
                )
            }

            binding.carousel5.setData(listFive)

            // ----------------------------------------------------------------
            // Example Six: Carousel without image
            // ----------------------------------------------------------------

            binding.carousel6.registerLifecycle(lifecycle)

            val colorsForSix = listOf(
                R.color.flat_awesome_green_1,
                R.color.flat_green_1,
                R.color.flat_blue_1,
                R.color.flat_pink_1,
                R.color.flat_yellow_1,
                R.color.flat_orange_1
            )

            // Custom view
            binding.carousel6.carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding? {
                    return ItemCustomFixedSizeLayout4Binding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemCustomFixedSizeLayout4Binding

                    val currentColor =
                        ResourcesCompat.getColor(resources, colorsForSix[position], null)

                    currentBinding.card.setCardBackgroundColor(currentColor)

                    currentBinding.tvCaption.text = "— ${DataSet.six[position].first}"
                    currentBinding.tvBody.text = DataSet.six[position].second
                }
            }

            val listSix = mutableListOf<CarouselItem>()

            for (item in DataSet.six) {
                listSix.add(CarouselItem())
            }

            binding.carousel6.setData(listSix)
        }
    }
}
