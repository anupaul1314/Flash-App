package com.example.flash.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flash.data.InternetItems
import com.google.firebase.auth.FirebaseAuth

enum class FlashAppScreen(val title:String) {
    Start("FlashCart"),
    Items("Choose Items"),
    Cart("Your Cart")
}
val auth = FirebaseAuth.getInstance()
var canNavigateBack = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashApp(
    flashViewModal: FlashViewModal = viewModel(),
    navController: NavHostController = rememberNavController()
   ) {
    val user by flashViewModal.user.collectAsState()
    auth.currentUser?.let { flashViewModal.setUser(it) }
    val isVisible by flashViewModal._isVisible.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FlashAppScreen.valueOf(
        backStackEntry?.destination?.route ?: FlashAppScreen.Start.name
    )
    canNavigateBack = navController.previousBackStackEntry != null
    val cartItems by flashViewModal.cartItems.collectAsState()

    if (isVisible) {
        OfferScren()
        } else if (user == null) {
            LoginUi(flashViewModal = flashViewModal)
        }
        else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row {
                            Text(
                                text = currentScreen.title,
                                fontSize = 26.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            if (currentScreen == FlashAppScreen.Start) {
                                Text(
                                    text = "(${cartItems.size})",
                                    fontSize = 26.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        if (canNavigateBack){
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.ArrowBack,
                                    contentDescription = "Back Button"
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                FlashAppBar(
                    navController = navController,
                    currentScreen = currentScreen,
                    cartItems = cartItems
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = FlashAppScreen.Start.name,
                Modifier.padding(it)
            ) {
                composable(route = FlashAppScreen.Start.name) {
                    StartScreen(
                        flashViewModal = flashViewModal,
                        onCategoryClicked = {
                            flashViewModal.updateSelectedCategory(it)
                            navController.navigate(FlashAppScreen.Items.name)
                        }
                    )
                }
                composable(route = FlashAppScreen.Items.name) {
                    InternetItemsScreen(
                        flashViewModal = flashViewModal,
                        itemUiState = flashViewModal.itemUiState
                    )
                }
                composable(route = FlashAppScreen.Cart.name) {
                    CartScreen(
                        flashViewModal = flashViewModal,
                        onHomeButtonClicked = {
                            navController.navigate(FlashAppScreen.Start.name) {
                                popUpTo(0)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FlashAppBar(
    navController: NavHostController,
    currentScreen: FlashAppScreen,
    cartItems: List<InternetItems>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 70.dp,
                vertical = 10.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .clickable {
                           navController.navigate(FlashAppScreen.Start.name){
                               popUpTo(0)
                           }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home"
            )
            Text(
                text = "Home",
                fontSize = 10.sp
            )
        }
        Column(
            modifier = Modifier
                .clickable {
                    if (currentScreen != FlashAppScreen.Cart ) {
                        navController.navigate(FlashAppScreen.Cart.name){
                            popUpTo(0)
                        }
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart"
                )
                if (cartItems.isNotEmpty())
                Card(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    colors = CardDefaults.cardColors(
                        Color.Red
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = 1.dp
                            ),
                        text = cartItems.size.toString(),
                        fontSize = 10.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Text(
                text = "Cart",
                fontSize = 10.sp
            )
        }
    }
}




