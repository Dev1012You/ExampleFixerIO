package com.test.conversion.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.test.conversion.R
import com.test.conversion.presentation.viewmodel.ConvertViewModel
import kotlinx.android.synthetic.main.fragment_convert.converted_currency_value as convertedCurrencyValueView
import kotlinx.android.synthetic.main.fragment_convert.converted_currency_name as convertedCurrencyNameView
import kotlinx.android.synthetic.main.fragment_convert.base_currency_name as baseCurrencyNameView
import kotlinx.android.synthetic.main.fragment_convert.base_currency_value as baseCurrencyValueInput

class ConvertFragment : BaseFragment<ConvertViewModel>() {
    override val layoutRes: Int = R.layout.fragment_convert
    override val viewModelClass = ConvertViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseCurrencyValueInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                viewModel.onInputValueChange(text.toString())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume(getConversionCurrency(), baseCurrencyValueInput.text.toString())
    }

    override fun observeViewModel(viewModel: ConvertViewModel) {
        viewModel.baseCurrencyName.observe(this, Observer { baseCurrencyNameView.text = it })
        viewModel.conversionCurrencyName.observe(this, Observer { convertedCurrencyNameView.text = it })
        viewModel.convertedValue.observe(this, Observer {
            convertedCurrencyValueView.setText(it)
        })
    }

    private fun getConversionCurrency(): String =
        arguments?.getString(ARG_CONVERSION_CURRENCY, null)
            ?: throw IllegalStateException("Conversion currency is missing")

    companion object {
        private const val ARG_CONVERSION_CURRENCY = "currency"
        fun newInstance(conversionCurrency: String) =
            ConvertFragment().apply {
                arguments = bundleOf(ARG_CONVERSION_CURRENCY to conversionCurrency)
            }
    }
}