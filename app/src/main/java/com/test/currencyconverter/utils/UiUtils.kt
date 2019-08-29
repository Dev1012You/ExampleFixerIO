package com.test.currencyconverter.utils

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.openFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String = Fragment::class.java.name
) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            android.R.animator.fade_in,
            android.R.animator.fade_out,
            android.R.animator.fade_in,
            android.R.animator.fade_out
        )
        .replace(containerId, fragment)
        .also { transaction ->
            if (addToBackStack) {
                transaction.addToBackStack(tag)
            }
        }
        .commit()
}