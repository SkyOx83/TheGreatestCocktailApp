package fr.isen.azzopardi.thegreatestcocktailapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.azzopardi.thegreatestcocktailapp.models.AppBarState
import fr.isen.azzopardi.thegreatestcocktailapp.screens.DrinksScreen
import fr.isen.azzopardi.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class DrinksActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val categoryID = intent.getStringExtra(CATEGORY) ?: ""
        setContent {
            val context = LocalContext.current
            val appBarState = remember { mutableStateOf(AppBarState()) }

            TheGreatestCocktailAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text(appBarState.value.title) },
                            navigationIcon = {
                                IconButton(
                                    onClick = { finish() }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }

                ) { innerPadding ->

                    DrinksScreen(
                        Modifier.padding(innerPadding),
                        categoryID,
                        { topBar -> appBarState.value = topBar }
                    )
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Life Cycle", "Drinks Activity Destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Life Cycle", "Drinks Activity Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Life Cycle", "Drinks Activity Resume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Life Cycle", "Drinks Activity Stop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Life Cycle", "Drinks Activity Pause")
    }

    companion object {
        const val CATEGORY = "category"
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TheGreatestCocktailAppTheme {
        Greeting2("Android")
    }
}