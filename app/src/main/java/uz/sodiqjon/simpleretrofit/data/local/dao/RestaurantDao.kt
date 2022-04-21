package uz.sodiqjon.simpleretrofit.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData
import java.util.concurrent.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getAllRestaurans(): kotlinx.coroutines.flow.Flow<List<RestaurantsData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<RestaurantsData>)

    @Query("DELETE FROM restaurant")
    suspend fun deleteAllRestaurants()
}