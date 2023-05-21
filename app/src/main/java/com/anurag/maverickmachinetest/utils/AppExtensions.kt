package com.anurag.maverickmachinetest.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(message: String){

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.showImage(urlToLoad: String){

    Glide.with(this).load(urlToLoad).into(this)
}