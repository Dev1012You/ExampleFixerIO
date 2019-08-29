package com.test.conversion.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.conversion.R
import com.test.conversion.presentation.viewmodel.ConversionRatesViewModel
import kotlinx.android.synthetic.main.fragment_conversion_rates.conversion_rates_list as conversionRatesView
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.conversion.di.Dependencies
import com.test.conversion.ui.utils.Navigator
import kotlinx.android.synthetic.main.fragment_conversion_rates.base_currency_view as baseCurrencyView
import kotlinx.android.synthetic.main.fragment_conversion_rates.progress_bar as progressView

class ConversionRatesFragment : BaseFragment<ConversionRatesViewModel>() {

    override val layoutRes: Int = R.layout.fragment_conversion_rates
    override val viewModelClass = ConversionRatesViewModel::class.java

    private lateinit var conversionRatesAdapter: ConversionRatesAdapter
    private val flagUtil = Dependencies.flagUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        conversionRatesAdapter = ConversionRatesAdapter(flagUtil) {
            (activity as? Navigator)?.navigate(ConvertFragment.newInstance(it.name))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    private fun setupViews() {
        val itemDecor = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        conversionRatesView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = conversionRatesAdapter
            addItemDecoration(itemDecor)
        }
    }

    override fun observeViewModel(viewModel: ConversionRatesViewModel) {
        viewModel.conversionRates.observe(this, Observer {
            conversionRatesAdapter.submitList(it)
        })

        viewModel.progress.observe(this, Observer { isInProgress ->
            if (isInProgress) {
                progressView.visibility = View.VISIBLE
            } else {
                progressView.visibility = View.GONE
            }
        })

        viewModel.baseCurrency.observe(this, Observer {
            baseCurrencyView.text = it
            baseCurrencyView.setCompoundDrawablesWithIntrinsicBounds(
                flagUtil.getFlagResource(it),
                0,
                0,
                0
            )
        })
    }

    companion object {
        fun newInstance() = ConversionRatesFragment()
    }
}
