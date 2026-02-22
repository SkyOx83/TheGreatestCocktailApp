package fr.isen.azzopardi.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil3.compose.AsyncImage
import fr.isen.azzopardi.thegreatestcocktailapp.DetailCocktailActivity
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.azzopardi.thegreatestcocktailapp.managers.FavoriteManager
import fr.isen.azzopardi.thegreatestcocktailapp.models.AppBarState

@Composable
fun FavoritesScreen(modifier: Modifier, onComposing: (AppBarState) -> Unit) {
    val context = LocalContext.current
    val favoritesManager = FavoriteManager()
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifeCycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    var favorites = remember {
        mutableStateOf<List<Drink>>(favoritesManager.getFavorites(context))
    }

    LaunchedEffect(lifeCycleState) {
        onComposing(
            AppBarState("Favorites")
        )

        when(lifeCycleState) {
            Lifecycle.State.RESUMED -> {
                favorites.value = favoritesManager.getFavorites(context)
            }
            else -> { }
        }
    }
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(favorites.value) { item ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, DetailCocktailActivity::class.java)
                        intent.putExtra(DetailCocktailActivity.DRINKID, item.idDrink)
                        context.startActivity(intent)
                    },
                shape = RoundedCornerShape(16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AsyncImage(
                        model = item.strDrinkThumb,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                CircleShape
                            )
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = item.strDrink ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.strCategory ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.strAlcoholic ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}