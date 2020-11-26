package com.android.own.loginapp.di.component

import com.android.own.loginapp.di.ActivityScope
import com.android.own.loginapp.di.module.ActivityModule
import com.android.own.loginapp.ui.home.HomeActivity
import com.android.own.loginapp.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: HomeActivity)

    fun inject(activity: MainActivity)


}