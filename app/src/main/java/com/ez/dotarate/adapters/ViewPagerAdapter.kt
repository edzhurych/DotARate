package com.ez.dotarate.adapters

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ez.dotarate.constants.REFRESH_OBSERVABLE_BOOLEAN_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.view.fragments.GamesFragment
import com.ez.dotarate.view.fragments.MphFragment


class ViewPagerAdapter(
    private val listOfFragments: ArrayList<Fragment>,
    fragmentActivity: Fragment,
    private val isNeedPositionToStartGames: ObservableBoolean,
    private val isNeedPositionToStartMph: ObservableBoolean,
    private val id32: Int
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        val result: Fragment
        val bundle = Bundle()

        when (position) {
            0 -> {
                bundle.putSerializable(REFRESH_OBSERVABLE_BOOLEAN_KEY, isNeedPositionToStartGames)
                bundle.putInt(USER_ID_KEY, id32)
                result = listOfFragments[0]
                result.arguments = bundle
            }
            1 -> {
                bundle.putSerializable(REFRESH_OBSERVABLE_BOOLEAN_KEY, isNeedPositionToStartMph)
                bundle.putInt(USER_ID_KEY, id32)
                result = listOfFragments[1]
                result.arguments = bundle
            }
            else -> {
                bundle.putSerializable(REFRESH_OBSERVABLE_BOOLEAN_KEY, isNeedPositionToStartGames)
                bundle.putInt(USER_ID_KEY, id32)
                result = listOfFragments[0]
                result.arguments = bundle
            }
        }

        return result
    }

    override fun getItemCount(): Int {
        return listOfFragments.size
    }
}