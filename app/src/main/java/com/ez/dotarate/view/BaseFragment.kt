package com.ez.dotarate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding>(clazz: KClass<VM>) : Fragment() {

    protected val vm: VM by viewModel(clazz = clazz)
    protected lateinit var vb: VB

    @LayoutRes
    protected abstract fun layout(): Int

    protected abstract fun afterCreateView(
        view: View,
        savedInstanceState: Bundle?
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout(), container, false)

        vb = DataBindingUtil.bind(view)!!

        afterCreateView(view, savedInstanceState)
        return view
    }
}