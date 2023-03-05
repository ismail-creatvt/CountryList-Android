package com.ismail.creatvt.countrylist.model.remote

import com.ismail.creatvt.countrylist.model.CountryDataSource
import com.ismail.creatvt.countrylist.model.remote.api.CountryApiService
import com.ismail.creatvt.countrylist.dependencyinjection.CoroutineScopeProvider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryRemoteDataSource @Inject constructor(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val countryApiService: CountryApiService
) : CountryDataSource {
    override fun getCountries(callback: CountryDataSource.LoadCountriesCallback) {
        coroutineScopeProvider.coroutineScope.launch(IO) {
            try {
                val countries = countryApiService.getCountries()
                callback.onCountriesLoaded(countries)
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }
}