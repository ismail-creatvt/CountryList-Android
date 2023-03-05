package com.ismail.creatvt.countrylist.view.provinceslist

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ismail.creatvt.countrylist.R
import com.ismail.creatvt.countrylist.model.response.Province
import com.ismail.creatvt.countrylist.databinding.ProvinceItemLayoutBinding
import com.ismail.creatvt.countrylist.utils.layoutInflater

class ProvinceListAdapter(provinceList: LiveData<List<Province>>, lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<ProvinceListAdapter.ProvinceItemViewHolder>() {

    private var provinces: List<Province> = listOf()
        set(value) {
            val oldList = field
            field = value
            DiffUtil.calculateDiff(ProvinceListDiffCallback(oldList, field)).dispatchUpdatesTo(this)
        }

    init {
        provinceList.observe(lifecycleOwner) {
            provinces = it
        }
    }

    class ProvinceItemViewHolder(val itemBinding: ProvinceItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProvinceItemViewHolder(
        DataBindingUtil.inflate(
            parent.layoutInflater,
            R.layout.province_item_layout,
            parent,
            false
        )
        // Should also attach a lifecycleOwner to the binding if we have any bindings with LiveData objects
        // otherwise LiveData changes won't be updated to the UI but in this case we don't have any LiveData in the binding
    )

    override fun getItemCount() = provinces.size

    override fun onBindViewHolder(holder: ProvinceItemViewHolder, position: Int) {
        holder.itemBinding.province = provinces[position]
    }

}