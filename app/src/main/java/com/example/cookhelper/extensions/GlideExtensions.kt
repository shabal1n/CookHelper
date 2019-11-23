package com.example.cookhelper.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cookhelper.R


fun ImageView.loadImage(
    url: String?,
    placeholder: Int = R.drawable.ic_kitchen_black_24dp
) {
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(placeholder)
        .into(this)
}
