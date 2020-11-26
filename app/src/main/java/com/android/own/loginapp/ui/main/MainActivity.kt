package com.android.own.loginapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.android.own.loginapp.R
import com.android.own.loginapp.base.BaseActivity
import com.android.own.loginapp.di.component.ActivityComponent
import com.android.own.loginapp.ui.home.HomeActivity
import com.android.own.loginapp.utils.Event
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel>(), View.OnClickListener {

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        btn_log_out.setOnClickListener(this)

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchLogin.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finishAffinity()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout ->
                callLogOut()
            else ->
                super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun callLogOut() {
        viewModel.doLogOut()
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_log_out -> viewModel.doLogOut()

        }

    }


}