package com.ismail.creatvt.countrylist.dependencyinjection

import com.ismail.creatvt.countrylist.model.CountryDataSource
import com.ismail.creatvt.countrylist.model.remote.CountryRemoteDataSource
import com.ismail.creatvt.countrylist.model.remote.api.CountryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CountryListModule {

    @Provides
    fun provideCountrySource(coroutineScopeProvider: CoroutineScopeProvider, countryApiService: CountryApiService):CountryDataSource {
        return CountryRemoteDataSource(coroutineScopeProvider, countryApiService)
    }

}