package uz.sodiqjon.simpleretrofit.sevices

import retrofit2.http.GET
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData

interface RestaurantApi {
    @GET("random_restaurant?size=20")
    suspend fun getRestaurans(): List<RestaurantsData>
}