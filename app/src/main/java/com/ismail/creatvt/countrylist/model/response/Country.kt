package com.ismail.creatvt.countrylist.model.response


data class Country(
    val id: Int? = null,

    val name: String? = null,

    val capital: String? = null,

    val countryCode: String? = null,

    val provincesCount: Int? = null,

    val region: String? = null,

    val subregion: String? = null,

    val phoneCode: String? = null,

    val currency: String? = null,

    val currencySymbol: String? = null,

    val latitude: Float? = null,

    val longitude: Float? = null
)