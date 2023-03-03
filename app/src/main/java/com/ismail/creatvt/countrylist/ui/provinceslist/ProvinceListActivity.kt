package com.ismail.creatvt.countrylist.ui.provinceslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ismail.creatvt.countrylist.R

class ProvinceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province_list)
        setTitle(R.string.provinces_list)
    }
}