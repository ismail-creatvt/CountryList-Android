package com.ismail.creatvt.countrylist.model

import com.ismail.creatvt.countrylist.model.response.Country

class CountryRepository private constructor(private val dataSource: CountryDataSource) :
    CountryDataSource {

    companion object {
        private var INSTANCE: CountryRepository? = null
        fun getInstance(
            dataSource: CountryDataSource
        ): CountryRepository {
            if (INSTANCE == null) {
                INSTANCE = CountryRepository(dataSource)
            }
            return INSTANCE!!
        }
    }

    override fun getCountries(callback: CountryDataSource.LoadCountriesCallback) {
        dataSource.getCountries(object : CountryDataSource.LoadCountriesCallback {
            override fun onCountriesLoaded(countries: List<Country>) {
                callback.onCountriesLoaded(countries)
            }

            override fun onError(t: Throwable) {
                callback.onError(t)
            }
        })
    }

}