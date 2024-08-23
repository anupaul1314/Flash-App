package com.example.flash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flash.R
import com.example.flash.data.InternetItemWithQuantity
import com.example.flash.data.InternetItems

@Composable
fun CartScreen(
    flashViewModal: FlashViewModal,
    onHomeButtonClicked: () -> Unit
) {
    val cartItems by flashViewModal.cartItems.collectAsState()
    val cartItemsWithQuantity = cartItems.groupBy { it }
        .map {
            (items,cartItems) -> InternetItemWithQuantity(
                items,
                cartItems.size
            )
        }
    if (cartItems.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.ic_cart_banner),
                    contentDescription = "Offer"
                )
            }
            item {
                Text(
                    text = "Review Items",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            items(cartItemsWithQuantity) {
                CartCard(
                    it.items,
                    flashViewModal,
                    it.quantity
                )
            }
            item {
                Text(
                    text = "Bill Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            val totalPrice = cartItems.sumOf {
                it.itemPrice *75/100
            }
            val handlingCharge = totalPrice*1/100
            val deliveryFee = 30
            val grandTotal = totalPrice+handlingCharge+deliveryFee

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(236,236,236,255)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        BillRow(itemName = "Item Total", itemPrice = totalPrice, fontWeight = FontWeight.Normal)
                        BillRow(itemName = "Handling Charges", itemPrice = handlingCharge, fontWeight = FontWeight.Light)
                        BillRow(itemName = "Delivery Fee", itemPrice = deliveryFee, fontWeight = FontWeight.Light)
                        Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 5.dp), color = Color.LightGray)
                        BillRow(itemName = "To Pay", itemPrice = grandTotal, fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_app_icon),
                contentDescription = "App Icon"
            )
            
            Text(
                modifier = Modifier
                    .padding(20.dp),
                text = "Your Cart is empty",
                fontWeight = FontWeight.Bold
            )
            FilledTonalButton(
                onClick = {
                    onHomeButtonClicked()
                }
            ) {
                Text(text = "Browse Products")
            }
        }
    }

}

@Composable
fun CartCard(
    cartItems:InternetItems,
    flashViewModal: FlashViewModal,
    cartItemQunatity: Int
) {
    Row (
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ){
        AsyncImage(
            modifier = Modifier
                .padding(start = 5.dp)
                .fillMaxHeight()
                .weight(4f),
            model = cartItems.imageUrl ,
            contentDescription = "Item Image"
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxHeight()
                .weight(4f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = cartItems.itemName,
                fontSize = 20.sp,
                maxLines = 1
            )
            Text(
                text = cartItems.itemQuantity,
                fontSize = 14.sp,
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxHeight()
                .weight(3f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Rs. ${cartItems.itemPrice}",
                fontSize = 20.sp,
                maxLines = 1,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
            Text(
                text = "Rs. ${cartItems.itemPrice * 75/100}",
                fontSize = 18.sp,
                maxLines = 1,
                color = Color(254,116,105,255)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Quantity: ${cartItemQunatity}",
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
            Card(
                modifier = Modifier
                    .clickable {
                        flashViewModal.removeFromCart(items = cartItems)
                    }
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(254,116,105,255)
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .fillMaxWidth(),
                    text = "Remove",
                    color = Color.White,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun BillRow(
    itemName: String,
    itemPrice:Int,
    fontWeight: FontWeight
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = itemName,fontWeight =fontWeight)
        Text(text = "Rs.$itemPrice",fontWeight = fontWeight)
    }
}