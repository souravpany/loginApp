package com.android.own.loginapp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.android.own.loginapp.base.BaseItemViewHolder
import com.android.own.loginapp.di.ViewModelScope
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}