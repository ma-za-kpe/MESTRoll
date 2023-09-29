package com.mest.mestroll.core.data.di

import com.mest.mestroll.core.data.repo.MestrollRepositoryImpl
import com.mest.mestroll.core.domain.MestrollRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindPopularDrinkRepository(repository: MestrollRepositoryImpl): MestrollRepository

}