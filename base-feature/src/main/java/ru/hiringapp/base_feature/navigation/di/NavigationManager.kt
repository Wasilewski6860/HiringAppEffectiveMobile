package ru.hiringapp.base_feature.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hiringapp.base_feature.navigation.NavigationManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager {
        return NavigationManager()
    }
}