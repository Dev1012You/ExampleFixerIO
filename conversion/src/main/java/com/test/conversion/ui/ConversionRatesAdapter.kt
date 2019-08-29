package com.test.conversion.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.test.conversion.R
import com.test.conversion.presentation.model.ConversionRateItem
import com.test.conversion.ui.utils.FlagUtil

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ConversionRateItem>() {
    override fun areItemsTheSame(
        oldItem: ConversionRateItem,
        newItem: ConversionRateItem
    ): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: ConversionRateItem,
        newItem: ConversionRateItem
    ): Boolean =
        oldItem.name == newItem.name && oldItem.value == newItem.value
}

class ConversionRatesAdapter(private val flagUtil: FlagUtil, private val onClick: (ConversionRateItem) -> Unit) :
    ListAdapter<ConversionRateItem, ConversionRateViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionRateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_conversion_rates, parent, false)
        return ConversionRateViewHolder(view, flagUtil)
    }

    override fun onBindViewHolder(holder: ConversionRateViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}