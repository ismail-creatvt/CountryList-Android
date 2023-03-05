package com.ismail.creatvt.countrylist.view.provinceslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ismail.creatvt.countrylist.R
import com.ismail.creatvt.countrylist.databinding.ActivityProvinceListBinding
import com.ismail.creatvt.countrylist.utils.COUNTRY_ID
import com.ismail.creatvt.countrylist.utils.COUNTRY_NAME
import com.ismail.creatvt.countrylist.utils.COUNTRY_PROVINCES_COUNT
import com.ismail.creatvt.countrylist.viewmodel.ProvinceListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProvinceListActivity : AppCompatActivity() {

    private val provinceListViewModel: ProvinceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityProvinceListBinding>(
            this,
            R.layout.activity_province_list
        )
        binding.lifecycleOwner = this

        setUpTitleAndSubtitle()

        val countryId = intent.getIntExtra(COUNTRY_ID, -1)

        binding.adapter = ProvinceListAdapter(provinceListViewModel.provinces, this)
        binding.viewModel = provinceListViewModel
        binding.countryId = countryId

        if (savedInstanceState == null) {
            provinceListViewModel.getAllProvinces(countryId)
        }
    }

    private fun setUpTitleAndSubtitle() {
        val countryName = intent.getStringExtra(COUNTRY_NAME)
        val provincesCount = intent.getIntExtra(COUNTRY_PROVINCES_COUNT, 0)

        title = countryName
        supportActionBar?.subtitle = getString(R.string.provinces_count, provincesCount)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}