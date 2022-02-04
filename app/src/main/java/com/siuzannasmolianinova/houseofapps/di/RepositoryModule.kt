package com.siuzannasmolianinova.houseofapps.di

import com.siuzannasmolianinova.houseofapps.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun providesRepository(impl: Repository.RepositoryImpl): Repository
}
