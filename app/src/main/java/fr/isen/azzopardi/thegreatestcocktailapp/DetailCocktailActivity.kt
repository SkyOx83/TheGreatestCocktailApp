package fr.isen.azzopardi.thegreatestcocktailapp

import android.os.Bundle
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
import fr.isen.azzopardi.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class DetailCocktailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetailCocktailScreen(Modifier.padding(innerPadding))
                }
            }
        }
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