package com.bitcode.sep_24_mvvm_demo.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewmodel.R
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun loadImageUrlToImageView(imageView: ImageView, value: String){
    Glide.with(imageView)
        .load(value)
        .error(com.bitcode.sep_24_mvvm_demo.R.drawable.ic_launcher_background)
        .placeholder(com.bitcode.sep_24_mvvm_demo.R.drawable.ic_launcher_background)
        .circleCrop()
        .into(imageView)


}
