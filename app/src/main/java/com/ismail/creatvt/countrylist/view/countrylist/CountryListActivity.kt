package com.ismail.creatvt.countrylist.view.countrylist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.ismail.creatvt.countrylist.R
import com.ismail.creatvt.countrylist.model.response.Country
import com.ismail.creatvt.countrylist.databinding.ActivityCountryListBinding
import com.ismail.creatvt.countrylist.utils.COUNTRY_ID
import com.ismail.creatvt.countrylist.utils.COUNTRY_NAME
import com.ismail.creatvt.countrylist.utils.COUNTRY_PROVINCES_COUNT
import com.ismail.creatvt.countrylist.view.provinceslist.ProvinceListActivity
import com.ismail.creatvt.countrylist.viewmodel.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListActivity : AppCompatActivity(), CountryListViewModel.NavigationListener {

    private val countriesViewModel: CountryListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCountryListBinding>(
            this,
            R.layout.activity_country_list
        )
        binding.lifecycleOwner = this
        setTitle(R.string.country_list)

        countriesViewModel.countries.observe(this) {
            supportActionBar?.subtitle = getString(R.string.countries_count, it.size)
        }

        countriesViewModel.setNavigationListener(this)

        binding.viewModel = countriesViewModel
        binding.adapter = CountryListAdapter(countriesViewModel, this)

        if (savedInstanceState == null) {
            countriesViewModel.getAllCountries()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        countriesViewModel.clearNavigationListener()
    }

    override fun onNavigateToProvinceList(country: Country) {
        if(country.provincesCount == 0) {
            // there will be no list to show if there are no provinces so don't navigate to ProvinceListActivity
            Toast.makeText(this, R.string.no_provinces_message, Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(Intent(this, ProvinceListActivity::class.java).apply {
            putExtra(COUNTRY_ID, country.id)
            putExtra(COUNTRY_NAME, country.name)
            putExtra(COUNTRY_PROVINCES_COUNT, country.provincesCount)
        })
    }
}