package com.ez.dotarate.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var vm: VM
    protected lateinit var vb: VB

    @LayoutRes
    protected abstract fun layout(): Int

    protected abstract fun afterCreate(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = DataBindingUtil.setContentView(this, layout())

        try {
            vm = ViewModelProvider(this, viewModelFactory).get(vmTypeClass)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

        afterCreate(savedInstanceState)
    }

    private val vmTypeClass: Class<VM>
        get() {
            try {
                val className =
                    (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].toString()
                val clazz = Class.forName(className.replace("class ", ""))
                @Suppress("UNCHECKED_CAST")
                return clazz as Class<VM>
            } catch (e: Exception) {
                throw IllegalStateException(e.message)
            }
        }
}
