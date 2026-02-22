package fr.isen.azzopardi.thegreatestcocktailapp.models

import androidx.compose.runtime.Composable
import fr.isen.azzopardi.thegreatestcocktailapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

enum class Category {
    BEER,
    COCKTAIL,
    COCOA,
    COFFE,
    LIQUOR,
    DRINK,
    PUNCH,
    SHAKE,
    SHOT,
    SOFT,
    ALCOHOLIC,
    NON_ALCOHOLIC,
    OTHER;

    companion object {
        fun allObjects(): List<Category> {
            return listOf(
                BEER,
                COCKTAIL,
                COCOA,
                COFFE,
                LIQUOR,
                DRINK,
                PUNCH,
                SHAKE,
                SHOT,
                SOFT,
                OTHER
            )
        }
        fun toString(category: Category): String {
            return when(category) {
                ALCOHOLIC -> "Alcoholic"
                NON_ALCOHOLIC -> "Non alcoholic"
                OTHER -> "Other / Unknown"
                BEER -> "Beer"
                COCKTAIL -> "Cocktail"
                COCOA -> "Cocoa"
                COFFE -> "Coffe"
                LIQUOR -> "Homemade Liquor"
                DRINK -> "Ordinary Drink"
                PUNCH -> "Punch / Party Drink"
                SHAKE -> "Shake"
                SHOT -> "Shot"
                SOFT -> "Soft Drink"
            }
        }

        @Composable
        fun colors(category: Category): List<Color> {
            return when(category) {
                ALCOHOLIC -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )

                NON_ALCOHOLIC -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )

                OTHER -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                BEER -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                COCKTAIL -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                COCOA -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                COFFE -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                LIQUOR -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                DRINK -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                PUNCH -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                SHAKE -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                SHOT -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
                SOFT -> listOf(
                    colorResource(R.color.chip_category_start),
                    colorResource(R.color.chip_category_end)
                )
            }
        }
    }
}