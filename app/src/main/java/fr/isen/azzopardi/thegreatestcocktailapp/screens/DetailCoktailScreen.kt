package fr.isen.azzopardi.thegreatestcocktailapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import fr.isen.azzopardi.thegreatestcocktailapp.R
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.CocktailResponse
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.azzopardi.thegreatestcocktailapp.managers.FavoriteManager
import fr.isen.azzopardi.thegreatestcocktailapp.models.AppBarState
import fr.isen.azzopardi.thegreatestcocktailapp.models.Category
import fr.isen.azzopardi.thegreatestcocktailapp.network.ApiClient
import retrofit2.Call
import retrofit2.Response

@Composable
fun RandomCocktailScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit){
    var drink = remember { mutableStateOf<Drink?>(null) }

    LaunchedEffect(Unit) {
        //drink.value = ApiClient.retrofit.getRandomCocktail().drinks?.first()
        onComposing (
            AppBarState("Random Cocktail",
                actions = { DetailCocktailTopButton(drink.value) })
        )
        val call = ApiClient.retrofit.getRandomCocktail()
        call.enqueue(object: retrofit2.Callback<CocktailResponse> {
            override fun onResponse(
                call: Call<CocktailResponse?>?,
                response: Response<CocktailResponse?>?
            ) {
                drink.value = response?.body()?.drinks?.first()
            }

            override fun onFailure(call: Call<CocktailResponse?>?, t: Throwable?) {
                Log.e("request", "getRandom failed ${t?.message}")
            }
        })
    }

    drink.value?.let { drink ->
        DetailCocktailScreen(modifier, drink)
    } ?:run {
        Text("Running")
    }
}

@Composable
fun DetailCocktailScreen(drinkId: String,onComposing: (AppBarState) -> Unit, modifier: Modifier) {
    var drink = remember { mutableStateOf<Drink?>(null) }

    LaunchedEffect(Unit) {
        onComposing (
            AppBarState("Cocktail Details",
                actions = { DetailCocktailTopButton(drink.value) })
        )
//        drink.value = ApiClient.retrofit.getRandom().drinks?.first()
        val call = ApiClient.retrofit.getDetailCocktail(drinkId)
        call.enqueue(object : retrofit2.Callback<CocktailResponse> {
            override fun onResponse(
                call: Call<CocktailResponse?>?,
                response: Response<CocktailResponse?>?
            ) {
                drink.value = response?.body()?.drinks?.first()
            }
            override fun onFailure(
                call: Call<CocktailResponse?>?,
                t: Throwable?
            ) {
                Log.e("request", "getrandom failed ${t?.message}")
            }
        })
    }

    drink.value?.let { drink ->
        DetailCocktailScreen(modifier, drink)
    } ?: run {
        Text("Loading")
    }
}
@Composable
fun DetailCocktailScreen(modifier: Modifier, drink: Drink) {
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
            modifier = modifier.fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = drink.strDrinkThumb,
                    contentDescription = stringResource(id = R.string.dog_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            colorResource(R.color.circle_border),
                            CircleShape
                        )
                )
            }
            Text(
                text = drink.strDrink.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = colorResource(R.color.white)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .shadow(50.dp, shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.verticalGradient(listOf(
                            colorResource(R.color.chip_category_start),
                            colorResource(R.color.chip_category_end)
                        ))
                    )
                ) {
                    Text(
                        text = drink.strCategory.toString(),
                        fontSize = 20.sp,
                        color = colorResource(R.color.white),
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Box(Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .shadow(50.dp, shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.verticalGradient(listOf(
                            colorResource(R.color.chip_alcoholic_start),
                            colorResource(R.color.chip_alcoholic_end)
                        ))
                    )
                ) {
                    Text(
                        text = drink.strAlcoholic.toString(),
                        fontSize = 20.sp,
                        color = colorResource(R.color.white),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Text(text = drink.strGlass.toString())
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00897B)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    drink.ingredientList().forEach { (ingredient, measure) ->

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        Color(0xFFFF9800),
                                        CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = ingredient,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = measure,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Preparation",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF6C00)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = drink.strInstructionsFR ?: drink.strInstructions ?: "",
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}


@Composable
fun DetailCocktailTopButton(drink: Drink?) {
    val context = LocalContext.current
    val favoritesManager = FavoriteManager()
    drink?.let { drink ->
        var isFavorites = remember {
            mutableStateOf<Boolean>(favoritesManager.isFavorite(drink, context))
        }

        IconButton({
            favoritesManager.toggleFavorite(drink, context)
            isFavorites.value = favoritesManager.isFavorite(drink, context)
        }) {
            Icon(
                imageVector = if (isFavorites.value) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                },
                contentDescription = "Localized description"
            )
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