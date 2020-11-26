package com.android.own.loginapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.own.loginapp.base.BaseActivity
import com.android.own.loginapp.data.repository.LoginRepository
import com.android.own.loginapp.ui.home.HomeViewModel
import com.android.own.loginapp.ui.main.MainViewModel
import com.android.own.loginapp.utils.ViewModelProviderFactory
import com.android.own.loginapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */

@Suppress("DEPRECATION")
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable, loginRepository: LoginRepository
    ): HomeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                schedulerProvider,
                compositeDisposable, loginRepository
            )
        }).get(HomeViewModel::class.java)



    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        loginRepository: LoginRepository
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(
                schedulerProvider,
                compositeDisposable, loginRepository
            )
        }).get(MainViewModel::class.java)


}