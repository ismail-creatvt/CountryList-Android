package com.ismail.creatvt.countrylist.dependencyinjection

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeProvider {
    val coroutineScope: CoroutineScope
}