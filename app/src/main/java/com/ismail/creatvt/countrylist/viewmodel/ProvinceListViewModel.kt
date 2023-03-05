package com.ismail.creatvt.countrylist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismail.creatvt.countrylist.UseCase
import com.ismail.creatvt.countrylist.UseCaseHandler
import com.ismail.creatvt.countrylist.model.response.Province
import com.ismail.creatvt.countrylist.domain.GetProvinces
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProvinceListViewModel @Inject constructor(
    private val useCaseHandler: UseCaseHandler,
    private val getProvinces: GetProvinces
) : ViewModel(), ErrorHandler {

    private val _provinces = MutableLiveData<List<Province>>()
    val provinces: LiveData<List<Province>>
        get() = _provinces

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Error?>(null)
    override val error: LiveData<Error?>
        get() = _error

    fun getAllProvinces(countryId: Int) {
        _isLoading.postValue(true)
        _error.postValue(null)

        useCaseHandler.execute(
            getProvinces,
            GetProvinces.RequestValues(countryId),
            object : UseCase.UseCaseCallback<GetProvinces.ResponseValue> {
                override fun onSuccess(response: GetProvinces.ResponseValue) {
                    Log.d("NetworkCallTag", "Response Received: ${response.provinces.size}")
                    _provinces.postValue(response.provinces)
                    _isLoading.postValue(false)
                }

                override fun onError(t: Throwable) {
                    Log.d("NetworkCallTag", "Error: ${t.message}")
                    _isLoading.postValue(false)
                    _error.postValue(
                        Error(
                            t.javaClass.simpleName,
                            t.message ?: "Something went wrong!",
                            countryId
                        )
                    )
                }
            })
    }

    override fun onReloadClicked(error:Error) {
        val countryId = (error.extra as? Int)?:return
        getAllProvinces(countryId)
    }

}