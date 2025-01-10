package com.example.android.movies.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Methods {

    const val defaultRate = "0.0"
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

    fun formatDate(date: String): String{
        var formatted = ""
        if(date.isNotEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val dateTest = LocalDate.parse(date)
                val formatter = DateTimeFormatter.ofPattern("d MMM yyyy")
                formatted = dateTest.format(formatter)
            }else{
                val dateFormat = SimpleDateFormat("d MMM yyyy")
                formatted = dateFormat.format(date)
            }
        }
        return formatted
    }

    fun getRate(rate: String): String{
        return if(rate.isNotEmpty()){
            val dividedRate = rate.split(".")
            dividedRate[0] + "." + dividedRate[1].take(1)
        }else{
            defaultRate
        }
    }
}