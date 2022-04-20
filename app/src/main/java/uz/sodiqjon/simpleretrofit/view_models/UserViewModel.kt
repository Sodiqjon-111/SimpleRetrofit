package uz.sodiqjon.simpleretrofit.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.sodiqjon.simpleretrofit.core.Resource
import uz.sodiqjon.simpleretrofit.data.local.dataSource.UserDataSource
import uz.sodiqjon.simpleretrofit.data.model.UserModel
import uz.sodiqjon.simpleretrofit.domain.repo_imp.AuthRepo
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: AuthRepo,
    private val dataSource: UserDataSource
) : ViewModel() {
    init {
        getUsers()
    }

    val userList = MutableLiveData<Resource<List<UserModel>>>()

    fun getUsers() {
        repository.getUser().observeForever {
            userList.value = it
        }
    }

    suspend fun saveUsers(users: UserModel) {
        viewModelScope.launch {
            dataSource.saveUser(users)
        }
    }

   suspend  fun getUserDetails() =
            dataSource.getUsers()



}