package com.aemerse.sliding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.aemerse.sliding.databinding.SelectedImageActivityBinding

class SelectedImage : AppCompatActivity() {
    private lateinit var binding: SelectedImageActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectedImageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.extras!!.get("file"))
            .into(binding.imagePreview)
    }
}