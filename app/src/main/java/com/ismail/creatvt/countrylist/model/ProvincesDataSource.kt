package com.ismail.creatvt.countrylist.model

import com.ismail.creatvt.countrylist.model.response.Province

interface ProvincesDataSource {

    interface LoadProvincesCallback {
        fun onProvincesLoaded(provinces: List<Province>)
        fun onError(t: Throwable)
    }

    fun getProvinces(countryId:Int, callback: LoadProvincesCallback)
}