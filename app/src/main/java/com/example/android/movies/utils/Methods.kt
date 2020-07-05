package com.example.android.movies.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

object Methods {

    fun setupToolbar(context: Context, toolbar: Toolbar, titleText: String, supportActionBar: ActionBar?){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = titleText
        toolbar.setTitleTextColor(ContextCompat.getColor(context, android.R.color.white))
        val iconBack: Drawable? = toolbar.navigationIcon
        iconBack?.setColorFilter(ContextCompat.getColor(context, android.R.color.white),  PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(iconBack)
    }

    fun imageUrl(context: Context, url: String, imageContainer: ImageView){
        Glide.with(context).load(url).into(imageContainer)
    }
}