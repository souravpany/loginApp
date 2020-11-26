package com.android.own.loginapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.android.own.loginapp.base.BaseViewModel
import com.android.own.loginapp.data.repository.LoginRepository
import com.android.own.loginapp.utils.Event
import com.android.own.loginapp.utils.Resource
import com.android.own.loginapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val loginRepository: LoginRepository
) : BaseViewModel(
    schedulerProvider, compositeDisposable
) {

    val titleNameField: MutableLiveData<String> = MutableLiveData()
    val titleEmailField: MutableLiveData<String> = MutableLiveData()
    val titlePasswordField: MutableLiveData<String> = MutableLiveData()
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val progressBar: MutableLiveData<Boolean> = MutableLiveData()


    fun onTitleNameChange(titleName: String) = titleNameField.postValue(titleName)
    fun onTitleEmailChange(titleEmail: String) = titleEmailField.postValue(titleEmail)
    fun onTitlePasswordChange(titlePassword: String) = titlePasswordField.postValue(titlePassword)


    override fun onCreate() {

        if (loginRepository.getCurrentUser() != null
        ) {

            launchMain.postValue(Event(emptyMap()))
        }

    }

    fun hideProgressBar() {
        progressBar.postValue(false)
    }

    fun showProgressBar() {
        progressBar.postValue(true)
    }

    fun doLogin() {
        val name = titleNameField.value
        val email = titleEmailField.value
        val password = titlePasswordField.value

        if (name != null && email != null && password != null) {
            showProgressBar()


            compositeDisposable.addAll(
                loginRepository.addNewLoginUserToDatabase(name, email, password)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {

                            hideProgressBar()
                            messageString.postValue(Resource.error("Login successfully"))
                            launchMain.postValue(Event(emptyMap()))
                        },
                        {
                            hideProgressBar()
                            messageString.postValue(Resource.error("Something went wrong"))
                        }
                    ))

        } else {
            messageString.postValue(Resource.error("Enter the details..."))
        }

    }
}