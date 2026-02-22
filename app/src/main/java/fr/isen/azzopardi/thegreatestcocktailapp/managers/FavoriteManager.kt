package fr.isen.azzopardi.thegreatestcocktailapp.managers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.Drink
import androidx.core.content.edit

data class Favorites (
    @SerializedName("favorite")
    var favorites: MutableList<Drink> = mutableListOf()
)

class FavoriteManager {
    fun getFavorites(context: Context): List<Drink> {
        val sharedPreferences = context.getSharedPreferences("favorites", 0)
        val favorites = sharedPreferences.getString(
            "favorites",
            null
        )
        if (favorites == null){
            return emptyList()
        }
        return Gson().fromJson(favorites, Array<Drink>::class.java).toMutableList()
    }
    fun toggleFavorite(drink: Drink, context: Context) {
        val sharedPreferences = context.getSharedPreferences("favorites", 0)
        val favorites = sharedPreferences.getString(
            "favorites",
            null
        )
        if (favorites == null) {
            sharedPreferences
                .edit()
                .putString(
                    "favorites",
                    Gson().toJson(mutableListOf(drink))
                )
                .apply()
            return
        }
        var list = Gson().fromJson(favorites, Array<Drink>::class.java).toMutableList()

        if(list.firstOrNull() { it.idDrink == drink.idDrink} != null) {
            list.removeAll {it.idDrink == drink.idDrink}
        } else {
            list.add(drink)
        }
        sharedPreferences
            .edit {
                putString(
                    "favorites",
                    Gson().toJson(list)
                )
            }
    }

    fun isFavorite(drink: Drink, context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("favorites", 0)
        val favorites = sharedPreferences.getString(
            "favorites",
            null
        )
        if(favorites == null){
            return false
        }
        var list = Gson().fromJson(favorites, Array<Drink>::class.java).toMutableList()

        return list.firstOrNull() { it.idDrink == drink.idDrink} != null
    }
}




