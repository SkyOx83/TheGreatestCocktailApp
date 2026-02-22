package fr.isen.azzopardi.thegreatestcocktailapp



import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.azzopardi.thegreatestcocktailapp.models.AppBarState
import fr.isen.azzopardi.thegreatestcocktailapp.screens.BottomAppBar
import fr.isen.azzopardi.thegreatestcocktailapp.screens.CategoriesScreen
import fr.isen.azzopardi.thegreatestcocktailapp.screens.RandomCocktailScreen
import fr.isen.azzopardi.thegreatestcocktailapp.screens.FavoritesScreen
import fr.isen.azzopardi.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

data class TabBarItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()

            val appBarState = remember { mutableStateOf(AppBarState()) }

            val randomItem = TabBarItem(stringResource(R.string.tab_item_random), Icons.Filled.Home, Icons.Outlined.Home)
            val categoryItem = TabBarItem(stringResource(R.string.tab_item_category), Icons.Filled.Menu, Icons.Outlined.Menu)
            val favoriteItem = TabBarItem(stringResource(R.string.tab_item_favorite), Icons.Filled.Favorite, Icons.Outlined.Favorite)
            val tabItems = listOf(randomItem, categoryItem, favoriteItem)
            TheGreatestCocktailAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar({
                            Text(appBarState.value.title)
                        }, actions = {
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
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomAppBar(tabItems, navController) }
                ) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    NavHost(navController, startDestination = randomItem.title) {
                        composable(randomItem.title) {
                            RandomCocktailScreen(
                                Modifier.padding(innerPadding),
                                { topBar ->
                                    appBarState.value = topBar
                                }
                            )
                        }
                        composable(categoryItem.title) {
                            CategoriesScreen(
                                Modifier.padding(innerPadding),
                                { topBar ->
                                    appBarState.value = topBar
                                }
                            )
                        }
                        composable(favoriteItem.title) {
                            FavoritesScreen(
                                Modifier.padding(innerPadding),
                                { topBar ->
                                    appBarState.value = topBar
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Life Cycle", "Main Activity Destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Life Cycle", "Main Activity Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Life Cycle", "Main Activity Resume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Life Cycle", "Main Activity Stop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Life Cycle", "Main Activity Pause")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) { // Horizontal Alignment
        Text("Hello $name!")
        Text("Hello Isen")
    }

//    Column(modifier = modifier) { /* Vertical Alignment */
//        Text("Hello $name!")
//        Text("Hello Isen")
//    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheGreatestCocktailAppTheme {
        Greeting("Android")
    }
}
