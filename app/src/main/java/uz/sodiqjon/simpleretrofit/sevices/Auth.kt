package uz.sodiqjon.simpleretrofit.sevices

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import uz.sodiqjon.simpleretrofit.data.model.*

interface Auth {
    @GET("posts")
    suspend fun getUsers(): Response<List<UserModel>>

//    @GET("photos")
//    suspend fun getImages(): Response<List<ImageModel>>


}