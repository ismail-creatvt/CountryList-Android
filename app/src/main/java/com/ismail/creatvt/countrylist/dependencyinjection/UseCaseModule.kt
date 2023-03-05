package com.ismail.creatvt.countrylist.dependencyinjection

import com.ismail.creatvt.countrylist.UseCaseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCaseHandler(): UseCaseHandler {
        return UseCaseHandler.getInstance()
    }

}