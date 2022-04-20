package uz.sodiqjon.simpleretrofit.domain.repo

import androidx.lifecycle.LiveData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.sodiqjon.simpleretrofit.domain.repo_imp.AuthRepo
import uz.sodiqjon.simpleretrofit.core.Resource
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData
import uz.sodiqjon.simpleretrofit.data.model.UserModel
import uz.sodiqjon.simpleretrofit.data.remote.performGetOperation
import uz.sodiqjon.simpleretrofit.data_source.AuthDataSource
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class AuthRepoImpl @Inject constructor(private val authDataSource: AuthDataSource) : AuthRepo {
    override fun getUser(): LiveData<Resource<List<UserModel>>> {
        return performGetOperation {
             authDataSource.getUsers()
        }
    }

//    override fun getBar(): LiveData<Resource<List<RestaurantsData>>> {
//        return performGetOperation {
//            authDataSource.getBar()
//        }
//    }

}