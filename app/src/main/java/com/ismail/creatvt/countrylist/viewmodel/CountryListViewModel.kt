package com.ismail.creatvt.countrylist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismail.creatvt.countrylist.UseCase
import com.ismail.creatvt.countrylist.UseCaseHandler
import com.ismail.creatvt.countrylist.model.response.Country
import com.ismail.creatvt.countrylist.domain.GetCountries
import com.ismail.creatvt.countrylist.view.countrylist.ItemClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val useCaseHandler: UseCaseHandler,
    private val getCountries: GetCountries
) : ViewModel(), ItemClickListener<Country>, ErrorHandler {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Error?>(null)
    override val error: LiveData<Error?>
        get() = _error

    // for simplicity sake we are storing single navigation listener
    // if we had multiple components that needed to listen to navigation then we would have to create
    // a hashmap with a key and use a WeakReference to avoid creating memory leaks if some component
    // forgot to clear their listener
    private var navigationListener: NavigationListener? = null

    fun setNavigationListener(listener: NavigationListener) {
        navigationListener = listener
    }

    fun clearNavigationListener() {
        navigationListener = null
    }

    fun getAllCountries() {
        _isLoading.postValue(true)
        _error.postValue(null)

        useCaseHandler.execute(
            getCountries,
            GetCountries.RequestValues(),
            object : UseCase.UseCaseCallback<GetCountries.ResponseValue> {
                override fun onSuccess(response: GetCountries.ResponseValue) {
                    Log.d("NetworkCallTag", "Response Received: ${response.countries.size}")
                    _isLoading.postValue(false)
                    _countries.postValue(response.countries)
                }

                override fun onError(t: Throwable) {
                    _isLoading.postValue(false)
                    _error.postValue(
                        Error(
                            t.javaClass.simpleName ?: t.javaClass.simpleName,
                            t.message ?: "Something went wrong!"
                        )
                    )
                    Log.d("NetworkCallTag", "Error: ${t.message}")
                }
            })
    }

    interface NavigationListener {

        fun onNavigateToProvinceList(country: Country)

    }

    override fun onItemClicked(item: Country) {
        navigationListener?.onNavigateToProvinceList(item)
    }

    override fun onReloadClicked(error: Error) {
        getAllCountries()
    }
}