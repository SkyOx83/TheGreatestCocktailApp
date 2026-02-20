package fr.isen.azzopardi.thegreatestcocktailapp.network

import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.CategoryListResponse
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.CocktailResponse
import fr.isen.azzopardi.thegreatestcocktailapp.dataClasses.DrinkFilterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random.php")
    fun getRandomCocktail(): Call<CocktailResponse>

    @GET("list.php?c=list")
    fun getCategories(): Call<CategoryListResponse>

    @GET("filter.php")
    fun getDrinksPreview(
        @Query("c") categoryID: String
    ): Call<DrinkFilterResponse>

    @GET("lookup.php")
    fun getDetailCocktail(
        @Query("i") drinkID: String
    ): Call<CocktailResponse>
}