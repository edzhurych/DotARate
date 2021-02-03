package com.ez.dotarate.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.viewModel.SplashViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        val vm = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)

        vm.liveData.observe(this, Observer {
            if (it == null) {
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)

                intent.putExtra(USER_ID_KEY, it.id)
                startActivity(intent)
                finish()
            }
        })
    }
}
