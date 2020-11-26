package com.android.own.loginapp

import android.app.Application
import com.android.own.loginapp.di.component.ApplicationComponent
import com.android.own.loginapp.di.component.DaggerApplicationComponent
import com.android.own.loginapp.di.module.ApplicationModule
import com.facebook.stetho.Stetho

/**
 *Stetho - for checking the data correctly adding into data base or not
 *
 * Run your app from Android Studio
 *
 *Visit chrome://inspect/#devices in your Chrome browser. Your app will be listed. Click the inspect link under its name and a new DevTools window will be launched.
 *
 * To view the database, expand “Web SQL” in the menu on the left and you’ll see your database listed.
 Click to expand to view the tables.
 *
 *
 * Follow url for google sign in- chrome-extension://klbibkeccnjlkjkiokjodocebajanakg/
 * suspended.html#ttl=Integrating%20Google%20Sign-In%20into%20Your%20Android%20App&pos=2200&uri=https://
 * developers.google.com/identity/sign-in/android/sign-in
 *
 *
 * follow url for facebook sign in - https://www.youtube.com/watch?v=q0jAFmB-wkU
 *
 * **/

class LoginApplication : Application() {


    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        injectDependencies()

    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

}
