package com.example.flash.data
import com.example.flash.R
object DataSource {
    fun loadCategories():List<Categories>{
        return listOf<Categories>(
            Categories(stringResourceId = R.string.Fruits, imageResourceId = R.drawable.ic_fruit),
            Categories(stringResourceId = R.string.Bread, imageResourceId = R.drawable.ic_bread),
            Categories(stringResourceId = R.string.Munchies, imageResourceId = R.drawable.ic_munchies),
            Categories(stringResourceId = R.string.Vegetables, imageResourceId = R.drawable.ic_vegetables)
        )
    }
}


