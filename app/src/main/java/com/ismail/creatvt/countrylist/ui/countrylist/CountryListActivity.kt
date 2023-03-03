package com.ismail.creatvt.countrylist.ui.countrylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.ismail.creatvt.countrylist.R
import com.ismail.creatvt.countrylist.databinding.ActivityCountryListBinding
import com.ismail.creatvt.countrylist.model.countryList

class CountryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCountryListBinding>(this, R.layout.activity_country_list)
        setTitle(R.string.country_list)
        supportActionBar?.subtitle = getString(R.string.countries_count, countryList.size)

        binding.adapter = CountryListAdapter(countryList)
    }
}