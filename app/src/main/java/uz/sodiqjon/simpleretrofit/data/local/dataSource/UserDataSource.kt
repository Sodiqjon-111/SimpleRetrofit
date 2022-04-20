package uz.sodiqjon.simpleretrofit.data.local.dataSource

import uz.sodiqjon.simpleretrofit.data.local.dao.UserDao
import uz.sodiqjon.simpleretrofit.data.model.UserModel
import javax.inject.Inject

class UserDataSource @Inject constructor(private val userDao: UserDao) {
    suspend fun saveUser(users: UserModel) {
        userDao.insert( users)
    }

    suspend fun getUsers() = userDao.getUser()
}