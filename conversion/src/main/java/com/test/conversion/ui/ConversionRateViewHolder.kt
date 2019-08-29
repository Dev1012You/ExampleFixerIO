package com.test.conversion.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.conversion.presentation.model.ConversionRateItem
import com.test.conversion.ui.utils.FlagUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_conversion_rates.view.flag_image as flagImageView
import kotlinx.android.synthetic.main.item_conversion_rates.view.conversion_rate as conversionRate
import kotlinx.android.synthetic.main.item_conversion_rates.view.currency_name as currencyNameView

class ConversionRateViewHolder(override val containerView: View, private val flagUtil: FlagUtil) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(conversionRateItem: ConversionRateItem, onClickListener: (ConversionRateItem) -> Unit) {
        containerView.currencyNameView.text = conversionRateItem.name
        containerView.conversionRate.text = conversionRateItem.value
        containerView.flagImageView.setImageResource(flagUtil.getFlagResource(conversionRateItem.name))

        containerView.setOnClickListener {
            onClickListener(conversionRateItem)
        }
    }
}