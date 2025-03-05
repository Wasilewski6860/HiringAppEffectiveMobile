package ru.hiringapp.main.feature.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hiringapp.base.resources.ColorResources
import ru.hiringapp.base.resources.DrawableResource
import ru.hiringapp.base.resources.StringResources
import javax.inject.Singleton

//ТУТ РЕСУРСЫ
@Module
@InstallIn(SingletonComponent::class)
interface ResourceBindsModule {

    @Binds
    @Singleton
    fun provideDrawableResources(impl: DrawableResourceImpl): DrawableResource

    @Binds
    @Singleton
    fun provideColorResources(impl: ColorResourceImpl): ColorResources

    @Binds
    @Singleton
    fun provideStringResources(impl: StringResourceImpl): StringResources
}