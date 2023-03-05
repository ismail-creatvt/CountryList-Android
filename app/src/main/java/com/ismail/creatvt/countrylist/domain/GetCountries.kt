package com.ismail.creatvt.countrylist.domain

import com.ismail.creatvt.countrylist.UseCase
import com.ismail.creatvt.countrylist.model.CountryDataSource
import com.ismail.creatvt.countrylist.model.response.Country
import javax.inject.Inject

class GetCountries @Inject constructor(private val dataSource: CountryDataSource) :
    UseCase<GetCountries.RequestValues, GetCountries.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues?) {
        dataSource.getCountries(object :
            CountryDataSource.LoadCountriesCallback {
            override fun onCountriesLoaded(countries: List<Country>) {
                val sortedCountries = countries.sortedBy { it.name }
                val responseValue = ResponseValue(sortedCountries)
                useCaseCallback?.onSuccess(responseValue)
            }

            override fun onError(t: Throwable) {
                useCaseCallback?.onError(t)
            }
        })
    }

    class RequestValues : UseCase.RequestValues
    class ResponseValue(val countries: List<Country>) : UseCase.ResponseValue
}