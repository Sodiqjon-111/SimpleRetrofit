package uz.sodiqjon.simpleretrofit.data_source


import com.google.gson.GsonBuilder
import retrofit2.Response
import uz.sodiqjon.simpleretrofit.core.Resource
import uz.sodiqjon.simpleretrofit.data.model.ErrorResponseModel

abstract class BaseDataSource {
     protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
         try {
             val response = call()
             if (response.isSuccessful) {
                 val body = response.body()
                 if (body != null) return Resource.Success(body)
             }
            // logd(response.errorBody().toString())
             val reader = response.errorBody()?.charStream()
             var messageText: String? = null
             if (reader != null) {
                 val gson = GsonBuilder().create()
                 val message =
                     gson.fromJson(
                         reader,
                         ErrorResponseModel::class.java
                     )
                 messageText = message.message
                // logd(message.message ?: "unknown")
             }
             return error(Exception("${response.code()} $messageText"), errorCode = response.code())
         } catch (e: Exception) {
             return error(e)
         }
     }

     private fun <T> error(exception: Exception, errorCode: Int = 400): Resource<T> {
         //logd(exception.message ?: exception.toString())
         return Resource.Failure(exception = exception, errorCode = errorCode)
     }
}