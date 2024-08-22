package com.example.flash.data
import androidx.annotation.StringRes
import com.example.flash.R
object DataSource {
    fun loadCategories():List<Categories>{
        return listOf<Categories>(
            Categories(stringResourceId = R.string.fresh_fruits, imageResourceId = R.drawable.ic_fruits),
            Categories(stringResourceId = R.string.bath_body, imageResourceId = R.drawable.ic_bathbody),
            Categories(stringResourceId = R.string.bread, imageResourceId = R.drawable.ic_breads),
            Categories(stringResourceId = R.string.kitchen_essentials, imageResourceId = R.drawable.ic_kitchen),
            Categories(stringResourceId = R.string.munchies, imageResourceId = R.drawable.ic_munchies),
            Categories(stringResourceId = R.string.packaged_food, imageResourceId = R.drawable.ic_packaged_foods),
            Categories(stringResourceId = R.string.stationary, imageResourceId = R.drawable.ic_stationary),
            Categories(stringResourceId = R.string.pet_food, imageResourceId = R.drawable.ic_pets),
            Categories(stringResourceId = R.string.sweet_tooth, imageResourceId = R.drawable.ic_sweets),
            Categories(stringResourceId = R.string.vegetables, imageResourceId = R.drawable.ic_vegetables),
            Categories(stringResourceId = R.string.beverages, imageResourceId = R.drawable.ic_beverages)
        )
    }

    fun loadItems(
        @StringRes categoryName: Int
    ): List<Item>{
        return listOf(
            Item(R.string.banana_robusta, R.string.fresh_fruits,"1kg",100, R.drawable.ic_banana),
            Item(R.string.shimla_apple, R.string.fresh_fruits,"1kg",250, R.drawable.ic_apple),
            Item(R.string.papaya_semi_ripe, R.string.fresh_fruits,"1kg",150, R.drawable.ic_papaya),
            Item(R.string.pomegranate, R.string.fresh_fruits,"500g",150, R.drawable.ic_pomegranate),
            Item(R.string.pineapple, R.string.fresh_fruits,"1kg",130, R.drawable.ic_pineapple),
            Item(R.string.pepsi_can, R.string.fresh_fruits,"1",40, R.drawable.ic_pepsi)
        ).filter {
            it.itemCategoryId == categoryName
        }
    }
}


