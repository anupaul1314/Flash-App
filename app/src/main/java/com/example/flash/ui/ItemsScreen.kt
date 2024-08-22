package com.example.flash.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flash.R
import com.example.flash.data.InternetItems

@Composable
fun ItemsScreen(
    flashViewModal: FlashViewModal,
    items: List<InternetItems>
){
    val flashUiState by flashViewModal.uiState.collectAsState()
    val selectedCategory = stringResource(id = flashUiState.selectedCategory)
    val database = items

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        item(
            span = { GridItemSpan(2) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = R.drawable.ic_items_banner),
                    contentDescription = "Offer"
                )
                Spacer(modifier = Modifier.height(3.dp))
                Card (
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(108,194,111,255)
                    )
                ){
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        text = "${stringResource(id = flashUiState.selectedCategory)} (${database.size})",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
        items(database){
            ItemCard(
                stringResourceId = it.itemName,
                imageResourceId = it.imageUrl,
                itemQuantity = it.itemQuantity,
                itemPrice = it.itemPrice,
                flashViewModal = flashViewModal
            )
        }
    }
}

@Composable
fun InternetItemsScreen(
    flashViewModal: FlashViewModal,
    itemUiState: FlashViewModal.ItemUiState
) {
    when (itemUiState) {
        is FlashViewModal.ItemUiState.Loading -> {
            LoadingScreen()
        }
        is FlashViewModal.ItemUiState.Success -> {
            ItemsScreen(flashViewModal = flashViewModal, items = itemUiState.items)
        }
        else -> {
            ErrorScreen(flashViewModal = flashViewModal)
        }
    }
}

@Composable
fun ItemCard(
    stringResourceId: String,
    imageResourceId: String,
    itemQuantity: String,
    itemPrice: Int,
    flashViewModal: FlashViewModal
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.size(195.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(248,221,248,255)
            )
        ) {
            Box {
               AsyncImage(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(110.dp),
                   model = imageResourceId,
                   contentDescription = stringResourceId
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
            text = stringResourceId,
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
                    flashViewModal.addToCart(
                        InternetItems(
                            itemName = stringResourceId,
                            itemQuantity = itemQuantity,
                            itemPrice = itemPrice,
                            imageUrl = imageResourceId,
                            itemCategory = ""
                        )
                    )
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

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_loading),
            contentDescription = "Loading image"
        )
    }
}

@Composable
fun ErrorScreen(
    flashViewModal: FlashViewModal
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "Error image"
        )
        Text(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = "Oops! Internet Unavailable. Please check your connection or retry after turning your wifi or mobile data on.",
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                flashViewModal.getFlashItems()
            }
        ) {
            Text(text = "Retry")
        }
    }
}
