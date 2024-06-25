package com.example.flash.ui
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import com.example.flash.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flash.data.DataSource

@Composable
fun StartScreen(){
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
                items(DataSource.loadCategories()){
                    CategoryCard(
                        context = context
                    )
                }
    }
}
@Composable
fun CategoryCard(context:Context){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White),
        modifier = Modifier
            .clickable {
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
                text = "Hello World",
                fontSize = 24.sp,
                modifier = Modifier.width(150.dp)
            )
        }
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(id =R.drawable.ic_fruit ) ,
            contentDescription ="Fresh Fruits"
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    StartScreen()
}