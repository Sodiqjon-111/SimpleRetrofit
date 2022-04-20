package uz.sodiqjon.simpleretrofit.data_source

import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.sodiqjon.simpleretrofit.sevices.Auth
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AuthDataSource @Inject constructor(private val auth: Auth) : BaseDataSource() {
    suspend fun getUsers() = getResult {
        auth.getUsers()
    }
//
//    suspend fun getBar() = getResult {
//        auth.getRestaurans()
//    }
}