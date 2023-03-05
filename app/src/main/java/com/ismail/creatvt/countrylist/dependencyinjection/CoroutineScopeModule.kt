package com.ismail.creatvt.countrylist.dependencyinjection

import android.content.Context
import com.ismail.creatvt.countrylist.CountryListApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoroutineScopeModule {
    @Provides
    fun provideCoroutineScopeProvider(@ApplicationContext context: Context): CoroutineScopeProvider {
        return (context as CountryListApplication)
    }
}