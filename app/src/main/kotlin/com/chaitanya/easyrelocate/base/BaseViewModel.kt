package com.chaitanya.easyrelocate.base

import android.arch.lifecycle.ViewModel
import com.chaitanya.easyrelocate.injection.component.DaggerViewModelInjector
import com.chaitanya.easyrelocate.injection.component.ViewModelInjector
import com.chaitanya.easyrelocate.injection.module.NetworkModule
import com.chaitanya.easyrelocate.ui.post.PostListViewModel
import com.chaitanya.easyrelocate.ui.post.PostViewModel

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
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
        }
    }
}