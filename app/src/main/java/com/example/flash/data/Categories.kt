package com.example.flash.data

import androidx.annotation.StringRes
import androidx.annotation.DrawableRes

data class Categories (
    @StringRes val stringResourceId :Int,
    @DrawableRes val imageResourceId : Int
)

