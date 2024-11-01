package com.example.myapplication.model

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector

data class Topic(
   @StringRes val name:Int,
    val noOfCourse:Int,
   @DrawableRes val image:Int,
)
