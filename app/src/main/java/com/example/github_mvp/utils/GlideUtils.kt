package com.example.github_mvp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.github_mvp.R

fun ImageView.getCircleCropImage(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}

fun ImageView.getUserDefaultImage() {
    Glide.with(context)
        .load(context.getDrawable(R.drawable.ic_baseline_person_24))
        .circleCrop()
        .into(this)
}