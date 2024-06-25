package com.example.flash.data
import com.example.flash.R
object DataSource {
    fun loadCategories():List<Categories>{
        return listOf<Categories>(
            Categories(stringResource = R.string.fruits, imageResource = R.drawable.ic_fruit),
            Categories(R.string.bread,R.drawable.ic_bread),
            Categories(R.string.munchies,R.drawable.ic_munchies),
            Categories(R.string.vegetables,R.drawable.ic_vegetables)
        )
    }
}


