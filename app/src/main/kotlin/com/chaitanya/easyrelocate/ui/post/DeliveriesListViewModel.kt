package com.chaitanya.easyrelocate.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.chaitanya.easyrelocate.base.BaseViewModel
import com.chaitanya.easyrelocate.model.Deliveries
import com.chaitanya.easyrelocate.model.DeliveriesDao
import com.chaitanya.easyrelocate.network.DeliveryAPI
import com.chaity.easyrelocate.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeliveriesListViewModel(private val deliveriesDao: DeliveriesDao): BaseViewModel(){
    @Inject
    lateinit var deliveryAPI: DeliveryAPI
    val deliveryListAdapter: DeliveriesListAdapter = DeliveriesListAdapter()

    lateinit var deliveryList:LiveData<PagedList<Deliveries>>

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadDeliveries() }

    private lateinit var subscription: Disposable

    init{
        loadDeliveries()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadDeliveries(){

        val deliveryDataSourceFactory = deliveriesDao.all

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build()

        deliveryList = LivePagedListBuilder(deliveryDataSourceFactory, pagedListConfig).build()

        subscription =  /*  Observable.fromCallable { deliveriesDao.all }
             .concatMap {
                    dbDeliveryList ->
                    if(dbDeliveryList.isEmpty())
                        deliveryAPI.getDeliveries().concatMap {
                            apiDeliveryList -> deliveriesDao.insertAll(*apiDeliveryList.toTypedArray())
                            Observable.just(apiDeliveryList)
                        }
                    else
                        Observable.just(dbDeliveryList)
                }*/

        deliveryAPI.getDeliveries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveDeliveryListStart() }
                .doOnTerminate { onRetrieveDeliveryListFinish() }
                .subscribe(
                        { result -> onRetrieveDeliveryListSuccess(result as PagedList<Deliveries>) },
                        { error-> onRetrieveDeliveryListError(error) }
                )
    }

    private fun onRetrieveDeliveryListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveDeliveryListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveDeliveryListSuccess(deliveryList: PagedList<Deliveries>){
        deliveryListAdapter.updatePostList(deliveryList)
    }

    private fun onRetrieveDeliveryListError(error: Throwable) {
        Log.e("onRetrieveDelListErr: ", error.localizedMessage)
        errorMessage.value = R.string.post_error
    }
}