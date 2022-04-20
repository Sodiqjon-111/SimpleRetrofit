package uz.sodiqjon.simpleretrofit.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import dagger.hilt.InstallIn
import uz.sodiqjon.simpleretrofit.data.model.UserModel

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(userModel: UserModel)

    @Query("SELECT * FROM USERMODEL")
    suspend fun getUser():List<UserModel>


}