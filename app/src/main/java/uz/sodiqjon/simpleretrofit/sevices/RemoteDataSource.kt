package uz.sodiqjon.simpleretrofit.sevices

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.sodiqjon.simpleretrofit.BuildConfig
import uz.sodiqjon.simpleretrofit.data.local.sharedPref.SharedPref
import uz.sodiqjon.simpleretrofit.application.Constants.BASE_URL
import uz.sodiqjon.simpleretrofit.application.Constants.BASU_URL_RESTAURANT
import java.util.concurrent.TimeUnit

class RemoteDataSource  (val sharedPref: SharedPref, val context: Context) {
    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        // val authenticator = TokenAuthenticator(sharedPref, buildTokenApi())
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(api)
    }

    fun <Api> buildRestaurantApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASU_URL_RESTAURANT)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(api)
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                }.build())
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    }

}