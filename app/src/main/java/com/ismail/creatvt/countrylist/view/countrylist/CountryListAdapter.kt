package com.ismail.creatvt.countrylist.view.countrylist

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ismail.creatvt.countrylist.R
import com.ismail.creatvt.countrylist.model.response.Country
import com.ismail.creatvt.countrylist.databinding.CountryItemLayoutBinding
import com.ismail.creatvt.countrylist.utils.layoutInflater
import com.ismail.creatvt.countrylist.viewmodel.CountryListViewModel

class CountryListAdapter(val viewModel: CountryListViewModel, lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<CountryListAdapter.CountryItemViewHolder>() {

    private var countries:List<Country> = listOf()
        set(value) {
            val oldList = field
            field = value
            DiffUtil.calculateDiff(CountryListDiffCallback(oldList, field)).dispatchUpdatesTo(this)
        }

    init {
        viewModel.countries.observe(lifecycleOwner) {
            countries = it
        }
    }

    class CountryItemViewHolder(val itemBinding: CountryItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryItemViewHolder(
        DataBindingUtil.inflate(
            parent.layoutInflater,
            R.layout.country_item_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.itemBinding.country = countries[position]
        holder.itemBinding.itemClickListener = viewModel
    }
}