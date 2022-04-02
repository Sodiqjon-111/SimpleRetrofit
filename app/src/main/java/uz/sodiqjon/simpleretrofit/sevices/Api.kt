package uz.sodiqjon.simpleretrofit.sevices

import retrofit2.Call
import retrofit2.http.GET
import uz.sodiqjon.simpleretrofit.data.model.BaseResponse
import uz.sodiqjon.simpleretrofit.data.model.ImageModel
import uz.sodiqjon.simpleretrofit.data.model.UserModel

interface Api {
    @GET("posts")
    fun getUsers():Call<List<UserModel>>

    @GET("photos")
    fun getImages():Call<List<ImageModel>>
}