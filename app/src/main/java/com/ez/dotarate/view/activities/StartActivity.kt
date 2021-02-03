package com.ez.dotarate.view.activities

import android.os.Bundle
import android.util.Log
import com.ez.dotarate.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class StartActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Log.d("MyLogs", "StartActivity. OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "StartActivity. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "StartActivity. onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "StartActivity. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "StartActivity. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "StartActivity. onDestroy")
    }
}
