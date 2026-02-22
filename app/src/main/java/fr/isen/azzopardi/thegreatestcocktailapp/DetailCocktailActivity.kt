package fr.isen.azzopardi.thegreatestcocktailapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.azzopardi.thegreatestcocktailapp.models.AppBarState
import fr.isen.azzopardi.thegreatestcocktailapp.screens.BottomAppBar
import fr.isen.azzopardi.thegreatestcocktailapp.screens.CategoriesScreen
import fr.isen.azzopardi.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.isen.azzopardi.thegreatestcocktailapp.screens.FavoritesScreen
import fr.isen.azzopardi.thegreatestcocktailapp.screens.RandomCocktailScreen
import fr.isen.azzopardi.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class DetailCocktailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drinkId = intent.getStringExtra(DRINKID) ?: ""
        enableEdgeToEdge()
        setContent {

            val appBarState = remember { mutableStateOf(AppBarState()) }

            TheGreatestCocktailAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar({
                            Text(appBarState.value.title)
                        }, navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        },
                            actions = {
//                            IconButton({
//                                Toast
//                                    .makeText(context, "Add to favorite", Toast.LENGTH_LONG)
//                                    .show()
//                            }) {
//                                Icon(
//                                    imageVector = Icons.Filled.FavoriteBorder,
//                                    contentDescription = "Localized description"
//                                )
//                            }
                            appBarState.value.actions?.invoke(this)

                        })
                    },
                modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    DetailCocktailScreen(drinkId,{ topBar ->
                        appBarState.value = topBar
                    },
                        Modifier.padding(innerPadding))

                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Life Cycle", "Detail Cocktail Activity Destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Life Cycle", "Detail Cocktail Activity Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Life Cycle", "Detail Cocktail Activity Resume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Life Cycle", "Detail Cocktail Activity Stop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Life Cycle", "Detail Cocktail Activity Pause")
    }

    companion object {
        const val DRINKID = "drinkid"
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    TheGreatestCocktailAppTheme {
        Greeting3("Android")
    }
}