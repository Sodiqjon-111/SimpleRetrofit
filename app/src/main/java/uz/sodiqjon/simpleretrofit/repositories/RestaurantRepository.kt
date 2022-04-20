package uz.sodiqjon.simpleretrofit.repositories

import androidx.room.Database
import androidx.room.withTransaction
import kotlinx.coroutines.delay
import uz.sodiqjon.simpleretrofit.data.AppDatabase
import uz.sodiqjon.simpleretrofit.data.local.dao.RestaurantDao
import uz.sodiqjon.simpleretrofit.data.remote.networkBoundResource
import uz.sodiqjon.simpleretrofit.domain.repo_imp.AuthRepo
import uz.sodiqjon.simpleretrofit.sevices.RestaurantApi
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val db: AppDatabase
) {
    private val restaurantDao = db.restaurantsDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurans()
        },
        fetch = {
            delay(2000)
            api.getRestaurans()
        },
        saveFetchResult = {
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(it)
            }

        }
    )
}