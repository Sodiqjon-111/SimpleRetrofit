package uz.sodiqjon.simpleretrofit.domain.repo_imp

import androidx.lifecycle.LiveData
import uz.sodiqjon.simpleretrofit.core.Resource
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData
import uz.sodiqjon.simpleretrofit.data.model.UserModel

interface AuthRepo {
    fun getUser(): LiveData<Resource<List<UserModel>>>
   // fun getBar(): LiveData<Resource<List<RestaurantsData>>>
}