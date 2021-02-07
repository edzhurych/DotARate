package com.ez.dotarate.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.viewmodel.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {

    private val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.liveUserId.observe(this, Observer {
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
