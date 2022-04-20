package uz.sodiqjon.simpleretrofit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.sodiqjon.simpleretrofit.data.local.dao.RestaurantDao
import uz.sodiqjon.simpleretrofit.data.local.dao.UserDao
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData
import uz.sodiqjon.simpleretrofit.data.model.UserModel

@Database(entities = [UserModel::class, RestaurantsData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun restaurantsDao(): RestaurantDao
}