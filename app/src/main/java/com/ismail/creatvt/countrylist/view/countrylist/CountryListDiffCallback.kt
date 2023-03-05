package com.ismail.creatvt.countrylist.view.countrylist

import com.ismail.creatvt.countrylist.model.response.Country
import com.ismail.creatvt.countrylist.view.SimpleDiffCallback

class CountryListDiffCallback(oldList: List<Country>, newList: List<Country>) :
    SimpleDiffCallback<Country>(oldList, newList) {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        // as the data class has in-built implementation of equals we don't need to check each property
        // so for simplicity I've directly used the equality operator on the objects itself
        return oldItem == newItem
    }
}