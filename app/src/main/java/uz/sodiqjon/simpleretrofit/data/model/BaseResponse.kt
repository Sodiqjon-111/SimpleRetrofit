package uz.sodiqjon.simpleretrofit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class BaseResponse<T>(
    val data: T?,
    val dataList: List<T>?,
    val httpStatus: String,
    val message: String,
    val success: Boolean
)

@Entity(tableName = "usermodel")
data class UserModel(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
)

data class ImageModel(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)

//data class RestaurantsModel(
//    val restauransName: List<RestaurantsData>
//)
@Entity(tableName = "restaurant")
data class RestaurantsData(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone_number: String,
    val type: String,
)
