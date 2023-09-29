package com.mest.mestroll.core.data.di

import com.mest.mestroll.core.data.local.preferences.Preferences
import com.mest.mestroll.core.data.local.preferences.PreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

  @Binds
  abstract fun providePreferences(preferences: PreferencesImpl): Preferences
}
