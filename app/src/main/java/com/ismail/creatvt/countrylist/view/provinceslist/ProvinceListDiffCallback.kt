package com.ismail.creatvt.countrylist.view.provinceslist

import com.ismail.creatvt.countrylist.model.response.Province
import com.ismail.creatvt.countrylist.view.SimpleDiffCallback

class ProvinceListDiffCallback(oldList: List<Province>, newList: List<Province>) :
    SimpleDiffCallback<Province>(oldList, newList) {
    override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
        return oldItem == newItem
    }
}