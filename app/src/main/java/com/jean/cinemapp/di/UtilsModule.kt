package com.jean.cinemapp.di

import android.content.Context
import com.jean.cinemapp.utils.BasePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UtilsModule {

    @Singleton
    @Provides
    fun provideBasePreferences(@ApplicationContext context: Context): BasePreferences = BasePreferences(context)
}