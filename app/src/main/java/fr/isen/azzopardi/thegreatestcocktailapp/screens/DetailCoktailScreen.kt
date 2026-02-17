package fr.isen.azzopardi.thegreatestcocktailapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.azzopardi.thegreatestcocktailapp.R
import fr.isen.azzopardi.thegreatestcocktailapp.models.Category


@Composable
fun DetailCocktailScreen(modifier: Modifier) {
    Box(
        Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.background_1),
                        colorResource(R.color.background_2),
                    )
                )
            )
            .fillMaxSize()
    ) {

        Column(
            modifier = modifier.fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cocktail),
                    contentDescription = stringResource(id = R.string.dog_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            colorResource(R.color.Circle),
                            CircleShape
                        )
                )
            }
            Text(
                text = "Yoghurt Cooler",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = colorResource(R.color.white)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                CategoryView(Category.OTHER)
                CategoryView(Category.NON_ALCOHOLIC)
            }
            Text(text = "Highball Glass")
            Card() {
                Column(
                    Modifier.padding(16.dp)
                        .fillMaxWidth()) {
                    Text(stringResource(R.string.ingrendient))
                    Text("Coca-cola")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                    Text("Lemon juice")
                }
            }
            Card() {
                Column(
                    Modifier.padding(16.dp)
                        .fillMaxWidth()) {
                    Text(stringResource(R.string.preparation))
                    Text("Take a glass, pourthe coke in the glass, then you take 7 drops of lemon juice")
                }
            }
        }
    }
}

@Composable
fun CategoryView(category: Category){
    Box(Modifier
        .padding(4.dp)
        .clip(CircleShape)
        .shadow(50.dp, shape = RoundedCornerShape(10.dp))
        .background(
            brush = Brush.verticalGradient(
                Category.colors(category)
            )
        )
    ) {
        Text(
            Category.toString(category),
            fontSize = 20.sp,
            color = colorResource(R.color.white),
            modifier = Modifier.padding(8.dp)
        )
    }
}