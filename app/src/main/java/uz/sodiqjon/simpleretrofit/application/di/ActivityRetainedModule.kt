package uz.sodiqjon.simpleretrofit.application.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.sodiqjon.simpleretrofit.domain.repo.AuthRepoImpl
import uz.sodiqjon.simpleretrofit.domain.repo_imp.AuthRepo

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(imp: AuthRepoImpl): AuthRepo
}