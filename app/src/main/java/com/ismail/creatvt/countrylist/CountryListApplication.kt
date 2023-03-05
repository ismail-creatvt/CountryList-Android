package com.ismail.creatvt.countrylist

import android.app.Application
import com.ismail.creatvt.countrylist.dependencyinjection.CoroutineScopeProvider
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class CountryListApplication : Application(), CoroutineScopeProvider {

    override val coroutineScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }
}