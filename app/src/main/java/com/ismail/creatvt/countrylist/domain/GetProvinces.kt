package com.ismail.creatvt.countrylist.domain

import com.ismail.creatvt.countrylist.UseCase
import com.ismail.creatvt.countrylist.model.ProvincesDataSource
import com.ismail.creatvt.countrylist.model.response.Province
import javax.inject.Inject

class GetProvinces @Inject constructor(private val dataSource: ProvincesDataSource) :
    UseCase<GetProvinces.RequestValues, GetProvinces.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues?) {
        dataSource.getProvinces(requestValues?.countryId ?: -1, object :
            ProvincesDataSource.LoadProvincesCallback {
            override fun onProvincesLoaded(provinces: List<Province>) {
                // Sort the list according to name and also move the capital city to the top
                val sortedProvinces = provinces.sortedWith { first, second ->
                    val isFirstCapital = first.country?.capital == first.name
                    val isSecondCapital = second.country?.capital == second.name
                    if (isFirstCapital) {
                        return@sortedWith -1
                    } else if (isSecondCapital) {
                        return@sortedWith 1
                    } else {
                        return@sortedWith (first.id ?: -1).compareTo(second.id ?: -1)
                    }
                }
                val responseValue = ResponseValue(sortedProvinces)
                useCaseCallback?.onSuccess(responseValue)
            }

            override fun onError(t: Throwable) {
                useCaseCallback?.onError(t)
            }
        })
    }

    class RequestValues(val countryId: Int) : UseCase.RequestValues
    class ResponseValue(val provinces: List<Province>) : UseCase.ResponseValue
}