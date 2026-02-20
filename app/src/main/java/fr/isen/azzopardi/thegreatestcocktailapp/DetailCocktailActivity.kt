package fr.isen.azzopardi.thegreatestcocktailapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.azzopardi.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.isen.azzopardi.thegreatestcocktailapp.screens.RandomCocktailScreen
import fr.isen.azzopardi.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class DetailCocktailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drinkId = intent.getStringExtra(DRINKID) ?: ""
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetailCocktailScreen(drinkId,Modifier.padding(innerPadding))
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