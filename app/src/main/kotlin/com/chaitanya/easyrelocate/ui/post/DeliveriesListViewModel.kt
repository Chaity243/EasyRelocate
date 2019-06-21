package com.chaitanya.easyrelocate.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
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
        subscription = Observable.fromCallable { deliveriesDao.all }
                .concatMap {
                    dbPostList ->
                    if(dbPostList.isEmpty())
                        deliveryAPI.getDeliveries().concatMap {
                            apiPostList -> deliveriesDao.insertAll(*apiPostList.toTypedArray())
                            Observable.just(apiPostList)
                        }
                    else
                        Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveDeliveryListStart() }
                .doOnTerminate { onRetrieveDeliveryListFinish() }
                .subscribe(
                        { result -> onRetrieveDeliveryListSuccess(result) },
                        { onRetrieveDeliveryListError() }
                )
    }

    private fun onRetrieveDeliveryListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveDeliveryListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveDeliveryListSuccess(deliveryList:List<Deliveries>){
        deliveryListAdapter.updatePostList(deliveryList)
    }

    private fun onRetrieveDeliveryListError(){
        errorMessage.value = R.string.post_error
    }
}