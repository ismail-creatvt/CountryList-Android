package com.ismail.creatvt.countrylist.dependencyinjection

import com.ismail.creatvt.countrylist.model.remote.ProvinceRemoteDataSource
import com.ismail.creatvt.countrylist.model.ProvincesDataSource
import com.ismail.creatvt.countrylist.model.remote.api.ProvinceApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProvinceListModule {
    @Provides
    fun provideProvinceSource(
        coroutineScopeProvider: CoroutineScopeProvider,
        provinceApiService: ProvinceApiService
    ): ProvincesDataSource {
        return ProvinceRemoteDataSource(coroutineScopeProvider, provinceApiService)
    }
}