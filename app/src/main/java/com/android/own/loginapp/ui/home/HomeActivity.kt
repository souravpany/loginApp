package com.android.own.loginapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.android.own.loginapp.ui.main.MainActivity
import com.android.own.loginapp.R
import com.android.own.loginapp.base.BaseActivity
import com.android.own.loginapp.di.component.ActivityComponent
import com.android.own.loginapp.utils.Event
import com.android.own.loginapp.utils.Toaster
import com.bumptech.glide.Glide
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<HomeViewModel>(), View.OnClickListener {

    private val RC_SIGNIN = 1001
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var callbackManager: CallbackManager? = null


    override fun provideLayoutId(): Int = R.layout.activity_home

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        // google login set-up
        googleLoginSetUp()

        // facebook login set-up
        facebookLoginSetUp()


        button_google.setOnClickListener(this)
        button_facebook.setOnClickListener(this)
        login_button_sign_in.setOnClickListener(this)


        login_edit_text_name.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTitleNameChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        login_edit_text_email.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTitleEmailChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        login_edit_text_password.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTitlePasswordChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })


    }

    private fun facebookLoginSetUp() {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {

                    val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                    ) { jsonObject, graphResponse -> // Application code

                        val first_name = jsonObject.getString("first_name")
                        val last_name = jsonObject.getString("last_name")
                        val email = jsonObject.getString("email")
                        val id = jsonObject.getString("id")
                        val image_url: String =
                            jsonObject.getJSONObject("picture").getJSONObject("data")
                                .getString("url")
                        setDetails("$first_name $last_name", email, image_url)

                    }
                    val parameters = Bundle()
                    parameters.putString(
                        "fields",
                        "first_name,last_name,email,id,picture.type(large)"
                    )
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })
    }

    private fun googleLoginSetUp() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    override fun setupObservers() {
        super.setupObservers()

        viewModel.titleNameField.observe(this, Observer {
            if (login_edit_text_name.text.toString() != it) login_edit_text_name.setText(it)
        })

        viewModel.titleEmailField.observe(this, Observer {
            if (login_edit_text_email.text.toString() != it) login_edit_text_email.setText(it)
        })

        viewModel.titlePasswordField.observe(this, Observer {
            if (login_edit_text_password.text.toString() != it) login_edit_text_password.setText(it)
        })

        viewModel.progressBar.observe(this, Observer {
            login_progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        })

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_google -> signInWithGoogle()
            R.id.button_facebook -> signInWithFaceBook()
            R.id.login_button_sign_in -> viewModel.doLogin()
        }
    }


    private fun signInWithGoogle() {
        viewModel.showProgressBar()
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGNIN)
    }


    private fun signInWithFaceBook() {
        viewModel.showProgressBar()
        LoginManager.getInstance()
            .logInWithReadPermissions(this, listOf("email", "public_profile"));
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

        if (requestCode == RC_SIGNIN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }


    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            val name = account!!.displayName
            val email = account.email
            val personPhoto: Uri? = account.photoUrl

            setDetails(name, email, personPhoto.toString())

            Toaster.show(applicationContext, "Name is -->> $name + Email is -->> $email")
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toaster.show(applicationContext, "Sign InResult:failed code=" + e.statusCode)
        }
    }


    private fun setDetails(name: String?, email: String?, imageUrl: String) {
        login_edit_text_name.setText(name)
        login_edit_text_email.setText(email)
        Glide.with(this)
            .load(imageUrl)
            .into(person_image_view)
        viewModel.hideProgressBar()
    }

}