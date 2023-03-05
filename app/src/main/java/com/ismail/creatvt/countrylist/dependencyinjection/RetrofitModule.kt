package com.ismail.creatvt.countrylist.dependencyinjection

import com.ismail.creatvt.countrylist.model.remote.api.CountryApiService
import com.ismail.creatvt.countrylist.model.remote.api.ProvinceApiService
import com.ismail.creatvt.countrylist.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryApiService(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProvinceApiService(retrofit: Retrofit): ProvinceApiService {
        return retrofit.create(ProvinceApiService::class.java)
    }
}