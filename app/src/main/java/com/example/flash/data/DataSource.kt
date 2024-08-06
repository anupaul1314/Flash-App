package com.example.flash.data
import com.example.flash.R
object DataSource {
    fun loadCategories():List<Categories>{
        return listOf<Categories>(
            Categories(stringResourceId = R.string.fresh_fruits, imageResourceId = R.drawable.ic_fruit),
            Categories(stringResourceId = R.string.Bread, imageResourceId = R.drawable.ic_bread),
            Categories(stringResourceId = R.string.Munchies, imageResourceId = R.drawable.ic_munchies),
            Categories(stringResourceId = R.string.Vegetables, imageResourceId = R.drawable.ic_vegetables)
        )
    }

    fun loadItems(): List<Item>{
        return listOf(
            Item(R.string.banana_robusta, R.string.fresh_fruits,"1kg",100, R.drawable.ic_banana),
            Item(R.string.shimla_apple, R.string.fresh_fruits,"1kg",250, R.drawable.ic_apple),
            Item(R.string.papaya_semi_ripe, R.string.fresh_fruits,"1kg",150, R.drawable.ic_papaya),
            Item(R.string.pomegranate, R.string.fresh_fruits,"500g",150, R.drawable.ic_pomegranate),
            Item(R.string.pineapple, R.string.fresh_fruits,"1kg",130, R.drawable.ic_pineapple),
            Item(R.string.pepsi_can, R.string.fresh_fruits,"1",40, R.drawable.ic_pepsi)
        )
    }
}


