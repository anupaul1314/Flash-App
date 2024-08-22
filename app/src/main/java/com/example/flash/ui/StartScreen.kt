package com.example.flash.ui
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flash.R
import com.example.flash.data.DataSource

@Composable
fun StartScreen(
    flashViewModal: FlashViewModal,
    onCategoryClicked: (Int) -> Unit
){
    val context = LocalContext.current
    val flashUiState by flashViewModal.uiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceBetween
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
                    painter = painterResource(id = R.drawable.ic_category_banner),
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
                        text = "Shop by Category",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        items(DataSource.loadCategories()){
                    CategoryCard(
                        context = context,
                        stringResourceId = it.stringResourceId,
                        imageResourceId = it.imageResourceId,
                        flashViewModal = flashViewModal,
                        onCategoryClicked = onCategoryClicked
                    )
                }
    }
}
@Composable
fun CategoryCard(
    context:Context,
    stringResourceId:Int,
    imageResourceId:Int,
    flashViewModal: FlashViewModal,
    onCategoryClicked: (Int) -> Unit
){
    val categoryName = stringResource(id = stringResourceId)
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .height(200.dp)
            .fillMaxWidth()
            .clickable {
                flashViewModal.updateClickText(categoryName)
                Toast
                    .makeText(context, "This $categoryName was added in cart", Toast.LENGTH_SHORT)
                    .show()
                onCategoryClicked(stringResourceId)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(248,221,248,255)
        )
        )
    {
        Column(
            modifier = Modifier
                .padding(start = 5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = categoryName,
                fontSize = 25.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(imageResourceId) ,
                contentDescription ="Fresh Fruits"
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
   StartScreen(
       flashViewModal = FlashViewModal(),
       onCategoryClicked = {}
   )
}