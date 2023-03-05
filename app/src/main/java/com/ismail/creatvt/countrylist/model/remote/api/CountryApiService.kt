package com.ismail.creatvt.countrylist.model.remote.api

import com.ismail.creatvt.countrylist.model.response.Country
import retrofit2.http.GET

interface CountryApiService {

    @GET("/countries")
    suspend fun getCountries():List<Country>

}