package com.ez.dotarate.extensions

import android.content.Intent
import android.util.Log
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ez.dotarate.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


// Map of tags
// Ключ - id графа
// Значение - тэг фрагмента по типу: "bottomNavigation#$index"
val graphIdToTagMap = SparseArray<String>()


/**
 * Manages the various graphs needed for a [BottomNavigationView].
 *
 * This sample is a workaround until the Navigation Component supports multiple back stacks.
 */
fun BottomNavigationView.setupWithNavController(
    navGraphIds: List<Int>,
    fragmentManager: FragmentManager,
    containerId: Int,
    intent: Intent,
    backStack: Stack<String>,
    liveNavController: MutableLiveData<NavController>
) {

    // First create a NavHostFragment for each NavGraph ID
    // Сначала создайте NavHostFragment для каждого NavGraph ID
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
            fragmentManager,
            fragmentTag,
            navGraphId,
            containerId
        )

        // Obtain its id
        val graphId = navHostFragment.navController.graph.id

        // Save to the map
        graphIdToTagMap[graphId] = fragmentTag

        // Attach or detach nav host fragment depending on whether it's the selected item.
        // Присоединить или отсоединить nav host fragment в зависимости от того, выбран ли он.
        if (this.selectedItemId == graphId) {
            // Update livedata with the selected graph
            liveNavController.value = navHostFragment.navController
            attachNavHostFragment(fragmentManager, navHostFragment, index == 0, backStack)
        } else {
            detachNavHostFragment(fragmentManager, navHostFragment)
        }
    }

    // Now connect selecting an item with swapping Fragments
    // Тэг выбранного фрагмента
    var selectedItemTag = graphIdToTagMap[this.selectedItemId]

    // When a navigation item is selected
    // Слушатель нажатий
    setOnNavigationItemSelectedListener { item ->
        // Don't do anything if the state is state has already been saved.
        if (fragmentManager.isStateSaved) {
            false
        } else {

            // Тэг нового выбранного элемента
            val newlySelectedItemTag = graphIdToTagMap[item.itemId]

            // NavHostFragment нового выбранного фрагмента
            val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                    as NavHostFragment

            fragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.nav_default_enter_anim,
                    R.anim.nav_default_exit_anim,
                    R.anim.nav_default_pop_enter_anim,
                    R.anim.nav_default_pop_exit_anim
                )
                .show(selectedFragment)
                .setPrimaryNavigationFragment(selectedFragment)
                .apply {
                    // Detach all other Fragments
                    graphIdToTagMap.forEach { _, fragmentTagIter ->
                        if (fragmentTagIter != newlySelectedItemTag) {
                            hide(fragmentManager.findFragmentByTag(selectedItemTag)!!)
                        }
                    }
                }
                .setReorderingAllowed(true)
                .commit()

            // Добавляем вкладку в стек или обновляем ее
            pushOnTopBackStack(newlySelectedItemTag, backStack)

            if (selectedItemTag != newlySelectedItemTag) {

                selectedItemTag = newlySelectedItemTag
                liveNavController.value = selectedFragment.navController
                true
            } else {
                false
            }
        }
    }

    // Optional: on item reselected, pop back stack to the destination of the graph
    setupItemReselected(graphIdToTagMap, fragmentManager, liveNavController)

    // Handle deep link
    setupDeepLinks(navGraphIds, fragmentManager, containerId, intent)
}

private fun BottomNavigationView.setupDeepLinks(
    navGraphIds: List<Int>,
    fragmentManager: FragmentManager,
    containerId: Int,
    intent: Intent
) {
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
            fragmentManager,
            fragmentTag,
            navGraphId,
            containerId
        )
        // Handle Intent
        if (navHostFragment.navController.handleDeepLink(intent)
            && selectedItemId != navHostFragment.navController.graph.id
        ) {
            this.selectedItemId = navHostFragment.navController.graph.id
        }
    }
}

private fun BottomNavigationView.setupItemReselected(
    graphIdToTagMap: SparseArray<String>,
    fragmentManager: FragmentManager,
    liveNavController: MutableLiveData<NavController>
) {
    setOnNavigationItemReselectedListener { item ->
        val newlySelectedItemTag = graphIdToTagMap[item.itemId]
        val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                as NavHostFragment
        val navController = selectedFragment.navController
        // Pop the back stack to the start destination of the current navController graph
        navController.popBackStack(
            navController.graph.startDestination, false
        )
        liveNavController.value = navController
    }
}

private fun detachNavHostFragment(
    fragmentManager: FragmentManager,
    navHostFragment: NavHostFragment
) {
    fragmentManager.beginTransaction()
        .hide(navHostFragment)
        .commitNow()
}

private fun attachNavHostFragment(
    fragmentManager: FragmentManager,
    navHostFragment: NavHostFragment,
    isPrimaryNavFragment: Boolean,
    backStack: Stack<String>
) {
    fragmentManager.beginTransaction()
        .show(navHostFragment)
        .apply {
            if (isPrimaryNavFragment) {
                pushOnTopBackStack(
                    graphIdToTagMap[navHostFragment.navController.graph.id],
                    backStack
                )
                setPrimaryNavigationFragment(navHostFragment)
            }
        }
        .commitNow()
}

private fun obtainNavHostFragment(
    fragmentManager: FragmentManager,
    fragmentTag: String,
    navGraphId: Int,
    containerId: Int
): NavHostFragment {
    // If the Nav Host fragment exists, return it
    val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
    existingFragment?.let { return it }

    // Otherwise, create it and return it.
    val navHostFragment = NavHostFragment.create(navGraphId)
    fragmentManager.beginTransaction()
        .add(containerId, navHostFragment, fragmentTag)
        .commitNow()
    return navHostFragment
}

private fun getFragmentTag(index: Int) = "bottomNavigation#$index"

private fun pushOnTopBackStack(
    navHostFragmentTag: String,
    backStack: Stack<String>
) {
    if (backStack.contains(navHostFragmentTag)) {
        backStack.remove(navHostFragmentTag)
        backStack.push(navHostFragmentTag)
    } else backStack.push(navHostFragmentTag)
}

fun popMyBackStack(
    fragmentManager: FragmentManager,
    backStack: Stack<String>,
    bottomNavigationView: BottomNavigationView
): NavController {
    val popHostFragmentTag = backStack.pop()
    backStack.remove(popHostFragmentTag)
    val popHostFragment = fragmentManager.findFragmentByTag(popHostFragmentTag) as NavHostFragment

    val previousHostFragmentTag = backStack.peek()
    val previousHostFragment =
        fragmentManager.findFragmentByTag(previousHostFragmentTag) as NavHostFragment

    bottomNavigationView.selectedItemId = previousHostFragment.navController.graph.id

    fragmentManager.beginTransaction()
        .setCustomAnimations(
            R.anim.nav_default_enter_anim,
            R.anim.nav_default_exit_anim,
            R.anim.nav_default_pop_enter_anim,
            R.anim.nav_default_pop_exit_anim
        )
        .show(previousHostFragment)
        .setPrimaryNavigationFragment(previousHostFragment)
        .hide(popHostFragment)
        .setReorderingAllowed(true)
        .commit()

    return previousHostFragment.navController
}