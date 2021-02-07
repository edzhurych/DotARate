package com.ez.dotarate.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding>(clazz: KClass<VM>) : AppCompatActivity() {

    protected val vm: VM by viewModel(clazz = clazz)
    protected lateinit var vb: VB

    @LayoutRes
    protected abstract fun layout(): Int

    protected abstract fun afterCreate(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = DataBindingUtil.setContentView(this, layout())

        afterCreate(savedInstanceState)
    }
}
