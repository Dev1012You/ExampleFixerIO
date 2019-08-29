package com.test.conversion.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.test.conversion.di.Dependencies

abstract class BaseFragment<VIEW_MODEL : ViewModel> : Fragment() {
    abstract val layoutRes: Int
    abstract val viewModelClass: Class<VIEW_MODEL>

    protected lateinit var viewModel: VIEW_MODEL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, Dependencies.viewModelFactory).get(viewModelClass)
        observeViewModel(viewModel)
    }

    protected open fun observeViewModel(viewModel: VIEW_MODEL) {}
}