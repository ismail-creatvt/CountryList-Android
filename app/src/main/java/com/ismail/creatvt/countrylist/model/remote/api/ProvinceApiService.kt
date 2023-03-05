package com.ismail.creatvt.countrylist.model.remote.api

import com.ismail.creatvt.countrylist.model.response.Province
import retrofit2.http.GET
import retrofit2.http.Path

interface ProvinceApiService {

    @GET("/provinces/{countryId}")
    suspend fun getProvinces(@Path("countryId") countryId: Int): List<Province>

}