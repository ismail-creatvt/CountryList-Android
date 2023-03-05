package com.ismail.creatvt.countrylist.model.remote

import com.ismail.creatvt.countrylist.model.ProvincesDataSource
import com.ismail.creatvt.countrylist.model.remote.api.ProvinceApiService
import com.ismail.creatvt.countrylist.dependencyinjection.CoroutineScopeProvider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProvinceRemoteDataSource @Inject constructor(
    private val coroutineScopeProvider: CoroutineScopeProvider,
    private val provinceApiService: ProvinceApiService
) : ProvincesDataSource {
    override fun getProvinces(countryId: Int, callback: ProvincesDataSource.LoadProvincesCallback) {
        coroutineScopeProvider.coroutineScope.launch(IO) {
            try {
                val countries = provinceApiService.getProvinces(countryId)
                callback.onProvincesLoaded(countries)
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }
}