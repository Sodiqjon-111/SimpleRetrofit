package uz.sodiqjon.simpleretrofit.application.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.sodiqjon.simpleretrofit.application.Constants.DATABASE_NAME
import uz.sodiqjon.simpleretrofit.data.AppDatabase
import uz.sodiqjon.simpleretrofit.data.local.sharedPref.SharedPref
import uz.sodiqjon.simpleretrofit.data.local.sharedPref.SharedPrefImpl
import uz.sodiqjon.simpleretrofit.sevices.Auth
import uz.sodiqjon.simpleretrofit.sevices.RemoteDataSource
import uz.sodiqjon.simpleretrofit.sevices.RestaurantApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPref =
        SharedPrefImpl(context)


    // retrofit
    @Singleton
    @Provides
    fun provideRemote(sharedPref: SharedPref, @ApplicationContext context: Context) =
        RemoteDataSource(sharedPref, context)

    //  dataBase
    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()


    //  for main api
    @Singleton
    @Provides
    fun provideWebService(remoteDataSource: RemoteDataSource): Auth =
        remoteDataSource.buildApi(Auth::class.java)

    // Another Api
    @Singleton
    @Provides
    fun provideWebServiceRest(remoteDataSource: RemoteDataSource): RestaurantApi =
        remoteDataSource.buildRestaurantApi(RestaurantApi::class.java)
}