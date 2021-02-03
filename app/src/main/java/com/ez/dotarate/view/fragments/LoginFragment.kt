package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentLoginBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.LoginViewModel


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun layout() = R.layout.fragment_login

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {

        val window = activity?.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark)

        vb.loginListener = loginListener
    }

    private val loginListener: View.OnClickListener = View.OnClickListener {
        // Можно получить NavController
        it.findNavController().navigate(R.id.steamFragment)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "LoginFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "LoginFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "LoginFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "LoginFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "LoginFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "LoginFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "LoginFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "LoginFragment. onDestroy")
    }
}