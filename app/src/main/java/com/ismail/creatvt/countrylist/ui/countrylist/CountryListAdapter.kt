package com.ismail.creatvt.countrylist.ui.countrylist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ismail.creatvt.countrylist.R
import com.ismail.creatvt.countrylist.databinding.CountryListItemLayoutBinding
import com.ismail.creatvt.countrylist.model.CountryData
import com.ismail.creatvt.countrylist.ui.provinceslist.ProvinceListActivity
import com.ismail.creatvt.countrylist.utils.layoutInflater

class CountryListAdapter(val countryList:ArrayList<CountryData>) : RecyclerView.Adapter<CountryListAdapter.CountryListItemViewHolder>() {

    class CountryListItemViewHolder(val itemBinding: CountryListItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryListItemViewHolder(
        DataBindingUtil.inflate(
            parent.layoutInflater,
            R.layout.country_list_item_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: CountryListItemViewHolder, position: Int) {
        holder.itemBinding.country = countryList[position]
        holder.itemBinding.countryCard.setOnClickListener {
            it.context.startActivity(Intent(it.context, ProvinceListActivity::class.java))
        }
    }
}