package com.android.own.loginapp.ui.main

import androidx.lifecycle.MutableLiveData
import com.android.own.loginapp.base.BaseViewModel
import com.android.own.loginapp.data.repository.LoginRepository
import com.android.own.loginapp.utils.Event
import com.android.own.loginapp.utils.Resource
import com.android.own.loginapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val loginRepository: LoginRepository
) : BaseViewModel(
    schedulerProvider, compositeDisposable
) {


    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    override fun onCreate() {

    }

    fun doLogOut() {

        launchLogin.postValue(Event(emptyMap()))

    }


}