package com.example.flash.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class FlashAppScreen() {
    Start,
    Items,
    Cart
}
@Composable
fun FlashApp(
    flashViewModal: FlashViewModal = viewModel(),
    navController: NavHostController = rememberNavController()
   ){
    val isVisible by flashViewModal._isVisible.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FlashAppScreen.values(
        backStackEntry?.destination?.route ?: FlashAppScreen.Start.name
    )
    canNavigateBack = navController.previousBackStackEntry ! = null

    if (isVisible){
        OfferScren()
    }else{
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = currentScreen)
                }
                )
            }
        ) {

        }
    }
    NavHost(navController = navController, startDestination = FlashAppScreen.Start.name ) {
        composable(route = FlashAppScreen.Start.name){
            StartScreen(
                flashViewModal = flashViewModal,
                onCategoryClicked = {
                    flashViewModal.updateSelectedCategory(it)
                    navController.navigate(FlashAppScreen.Items.name)
                }
            )
        }
        composable(route = FlashAppScreen.Items.name){
            ItemsScreen(flashViewModal = flashViewModal)
        }
    }
}





