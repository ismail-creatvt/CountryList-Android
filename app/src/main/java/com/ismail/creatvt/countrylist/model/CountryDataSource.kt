package com.ismail.creatvt.countrylist.model

import com.ismail.creatvt.countrylist.model.response.Country

interface CountryDataSource {

    interface LoadCountriesCallback {
        fun onCountriesLoaded(countries: List<Country>)
        fun onError(t: Throwable)
    }

    interface SaveTaskCallback {
        fun onSaveSuccess()
        fun onError(t: Throwable)
    }

    fun getCountries(callback: LoadCountriesCallback)
}