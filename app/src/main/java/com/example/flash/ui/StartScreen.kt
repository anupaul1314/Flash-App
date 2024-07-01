package com.example.flash.ui
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.flash.data.DataSource

@Composable
fun StartScreen(flashViewModal: ViewModel){
    val context = LocalContext.current
    val flashUiState by flashViewModal.uiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Text(text = flashUiState.clickStatus)
        }
                items(DataSource.loadCategories()){
                    CategoryCard(
                        context = context,
                        stringResourceId = it.stringResourceId,
                        imageResourceId = it.imageResourceId,
                        flashViewModal = flashViewModal
                    )
                }
    }
}
@Composable
fun CategoryCard(
    context:Context,
    stringResourceId:Int,
    imageResourceId:Int,
    flashViewModal: ViewModel
){
    val categoryName = stringResource(id = stringResourceId)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White),
        modifier = Modifier
            .clickable {
                flashViewModal.updateClickText(categoryName)
                Toast.makeText(
                    context, "This card was clicked",
                    Toast.LENGTH_SHORT).show()
            },
        )
    {
        Column(
            modifier = Modifier
                .padding(start = 5.dp)
        ) {
            Text(
                text = categoryName,
                fontSize = 24.sp,
                modifier = Modifier.width(150.dp)
            )
        }
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(imageResourceId) ,
            contentDescription ="Fresh Fruits"
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
   StartScreen(flashViewModal = FlashViewModal())
}