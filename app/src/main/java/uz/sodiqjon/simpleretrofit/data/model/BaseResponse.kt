package uz.sodiqjon.simpleretrofit.data.model

data class BaseResponse<T>(
    val data: T?,
    val dataList: List<T>?,
    val httpStatus: String,
    val message: String,
    val success: Boolean
)


data class UserModel(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String,
)

data class ImageModel(
    val albumId:Int,
    val id:Int,
    val title:String,
    val url:String,
    val thumbnailUrl:String,
)