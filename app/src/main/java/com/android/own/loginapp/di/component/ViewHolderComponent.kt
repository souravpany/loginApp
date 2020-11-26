package com.android.own.loginapp.di.component


import com.android.own.loginapp.di.ViewModelScope
import com.android.own.loginapp.di.module.ViewHolderModule
import dagger.Component

@ViewModelScope
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    //fun inject(viewHolder: ToDoItemViewHolder)
}