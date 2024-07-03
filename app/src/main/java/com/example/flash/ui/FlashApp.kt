package com.example.flash.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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





