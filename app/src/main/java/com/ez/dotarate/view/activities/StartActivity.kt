package com.ez.dotarate.view.activities

import android.os.Bundle
import android.util.Log
import com.ez.dotarate.R
import com.ez.dotarate.databinding.ActivityStartBinding
import com.ez.dotarate.view.BaseActivity

class StartActivity : BaseActivity<Nothing, ActivityStartBinding>(Nothing::class) {

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

    override fun layout(): Int = R.layout.activity_start

    override fun afterCreate(savedInstanceState: Bundle?) {

    }
}
