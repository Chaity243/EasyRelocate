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
     * @param deliveryListViewModel DeliveriesListViewModel in which to inject the dependencies
     */
    fun inject(deliveryListViewModel: DeliveriesListViewModel)
    /**
     * Injects required dependencies into the specified DeliveriesViewModel.
     * @param deliveryViewModel DeliveriesViewModel in which to inject the dependencies
     */
    fun inject(deliveryViewModel: DeliveriesViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}