package com.ismail.creatvt.countrylist.viewmodel

import androidx.lifecycle.LiveData

interface ErrorHandler {

    val error: LiveData<Error?>

    fun onReloadClicked(error: Error)

}