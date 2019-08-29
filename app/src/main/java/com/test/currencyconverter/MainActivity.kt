package com.test.currencyconverter

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.conversion.di.Dependencies
import com.test.conversion.ui.ConversionRatesFragment
import com.test.conversion.ui.utils.Navigator
import com.test.currencyconverter.utils.openFragment
import kotlinx.android.synthetic.main.activity_main.main_toolbar as toolbar

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dependencies.init(applicationContext)
        setContentView(R.layout.activity_main)
        openFragment(R.id.main_activity_fragment_container, ConversionRatesFragment.newInstance(), false)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    override fun navigate(fragment: Fragment) {
        openFragment(R.id.main_activity_fragment_container, fragment, true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}