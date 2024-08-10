package com.example.flash.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flash.R
import com.example.flash.data.DataSource

@Composable
fun ItemsScreen(flashViewModal: FlashViewModal){
    val flashUiState by flashViewModal.uiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(DataSource.loadItems(
            flashUiState.selectedCategory
        )){
            ItemCard(
                stringResourceId = it.stringResourceId,
                imageResourceId = it.imageResourceId,
                itemQuantity = it.itemQuantityId,
                itemPrice = it.itemPrice
            )
        }
    }
}

@Composable
fun ItemCard(
    stringResourceId: Int,
    imageResourceId: Int,
    itemQuantity: String,
    itemPrice: Int
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.size(195.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(255,182,193,100)
            )
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    painter = painterResource(id = imageResourceId),
                    contentDescription = "Item Name"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(244,67,54,255)
                        )
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                            text = "25% Off",
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            maxLines = 1,
            textAlign = TextAlign.Left,
            text = stringResource(id = stringResourceId),
            fontSize = 12.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Rs. $itemPrice",
                    fontSize = 7.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color(109,109,109,255),
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    text = "Rs. ${itemPrice*75/100}",
                    fontSize = 10.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color(255,116,105,255)
                )
            }
            Text(
                text = itemQuantity,
                fontSize = 14.sp,
                maxLines = 1,
                color = Color(114,114,114,255)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    Toast
                        .makeText(context, "Added to Cart", Toast.LENGTH_LONG)
                        .show()
                },
            colors = CardDefaults.cardColors(
                containerColor = Color(108,194,111,255)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Add to Cart",
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun bdskjcas() {
    ItemCard(
        stringResourceId = R.string.shimla_apple,
        imageResourceId = R.drawable.ic_apple,
        itemQuantity = "2kg",
        itemPrice = 100
    )
}