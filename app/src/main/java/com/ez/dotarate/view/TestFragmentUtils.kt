package com.ez.dotarate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ez.dotarate.R

object TestFragmentUtils {

    inline fun <reified F : Fragment> launchFragmentScenario(
        bundle: Bundle?, fragment: F, navController: NavController, initialState: Lifecycle.State
    ): FragmentScenario<F> {
        return launchFragmentInContainer(bundle, R.style.Theme_AppCompat, initialState) {
            fragment.also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { lifeCycleOwner ->
                    if (lifeCycleOwner != null) {
                        // The fragment’s view has just been created
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
    }
}