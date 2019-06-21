package com.chaitanya.easyrelocate.base

import android.arch.lifecycle.ViewModel
import com.chaitanya.easyrelocate.injection.component.DaggerViewModelInjector
import com.chaitanya.easyrelocate.injection.component.ViewModelInjector
import com.chaitanya.easyrelocate.injection.module.NetworkModule
import com.chaitanya.easyrelocate.ui.post.DeliveriesListViewModel
import com.chaitanya.easyrelocate.ui.post.DeliveriesViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is DeliveriesListViewModel -> injector.inject(this)
            is DeliveriesViewModel -> injector.inject(this)
        }
    }
}