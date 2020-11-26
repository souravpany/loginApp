package com.android.own.loginapp.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.android.own.loginapp.LoginApplication
import com.android.own.loginapp.data.local.db.DatabaseService
import com.android.own.loginapp.data.repository.LoginRepository
import com.android.own.loginapp.di.ApplicationContext
import com.android.own.loginapp.di.module.ApplicationModule
import com.android.own.loginapp.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {


    fun inject(app: LoginApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getSharedPreferences(): SharedPreferences

    fun getDatabaseService(): DatabaseService

    fun getCompositeDisposable(): CompositeDisposable

    fun getSchedulerProvider(): SchedulerProvider


    fun getLoginRepository(): LoginRepository

}
