package com.ez.dotarate.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ez.dotarate.view.profile.ProfileFragment
import com.ez.dotarate.view.profile.ProfileViewModel

class MyFragmentFactory(private val viewModel: ProfileViewModel) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        println("mylogs. MyFragmentFactory. classLoader - $classLoader; className - $className")

        return when(className) {
            ProfileFragment::class.java.name -> {
                ProfileFragment(viewModel)
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}