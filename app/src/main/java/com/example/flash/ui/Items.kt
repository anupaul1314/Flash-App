package com.example.flash.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Items(
    @StringRes val stringResourceId: Int,
   @StringRes val itemCategoryId: Int,
    val itemQuantityId: String,
    val itemPrice: Int,
    @DrawableRes val imageResource: Int
)
