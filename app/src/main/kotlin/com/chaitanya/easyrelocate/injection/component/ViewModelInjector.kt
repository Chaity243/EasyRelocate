package com.chaitanya.easyrelocate.injection.component

import dagger.Component
import com.chaitanya.easyrelocate.injection.module.NetworkModule
import com.chaitanya.easyrelocate.ui.post.DeliveriesListViewModel
import com.chaitanya.easyrelocate.ui.post.DeliveriesViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified DeliveriesListViewModel.
     * @param postListViewModel DeliveriesListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: DeliveriesListViewModel)
    /**
     * Injects required dependencies into the specified DeliveriesViewModel.
     * @param postViewModel DeliveriesViewModel in which to inject the dependencies
     */
    fun inject(postViewModel: DeliveriesViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}