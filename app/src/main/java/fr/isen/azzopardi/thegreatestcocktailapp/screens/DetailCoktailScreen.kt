package fr.isen.azzopardi.thegreatestcocktailapp.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import fr.isen.azzopardi.thegreatestcocktailapp.R
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.CocktailResponse
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.Drink
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
fun DetailCocktailScreen(drinkId: String, modifier: Modifier) {
    var drink = remember { mutableStateOf<Drink?>(null) }

    LaunchedEffect(Unit) {
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
            modifier = modifier.fillMaxWidth()
                .padding(8.dp)
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
                            colorResource(R.color.Circle),
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
                            colorResource(R.color.teal_200),
                            colorResource(R.color.teal_700)
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
                            colorResource(R.color.orange_200),
                            colorResource(R.color.orange_700)
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
            Card() {
                Column(
                    Modifier.padding(8.dp)
                        .fillMaxWidth()) {
                    Text(stringResource(R.string.ingrendient),
                        style = MaterialTheme.typography.titleMedium)
                    drink.ingredientList().forEach { (ingredient, measure) ->
                        Row {
                            Text(text = "• ")
                            Text(text = "$ingredient $measure")
                        }
                    }
                }
            }
            Card() {
                Column(
                    Modifier.padding(16.dp)
                        .fillMaxWidth()) {
                    Text(stringResource(R.string.preparation),
                        style = MaterialTheme.typography.titleMedium)
                    Text(text = drink.strInstructionsFR.toString())
                }
            }
        }
    }
}

@Composable
fun DetailCocktailTopButton(drink: Drink?) {
    val context = LocalContext.current
    IconButton({
        Toast
            .makeText(context, "Add to favorite", Toast.LENGTH_LONG)
            .show()
    }) {
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Localized description"
        )
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