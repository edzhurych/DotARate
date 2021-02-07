package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ez.domain.model.User
import com.ez.dotarate.R
import com.ez.dotarate.adapters.ViewPagerAdapter
import com.ez.dotarate.constants.PROFILE_TAB
import com.ez.dotarate.constants.SEARCH_TAB
import com.ez.dotarate.constants.TAB_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentProfileSearchBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.viewModel.ProfileSearchViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileSearchFragment : BaseFragment<ProfileSearchViewModel, FragmentProfileSearchBinding>() {
    private var mOldVerticalOffset = 0

    override fun layout() = R.layout.fragment_profile_search

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "ProfileSearchFragment. AfterCreateView!!!!!!!!!!!")
        // Enable back button
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        vb.vm = vm

        val id32 = requireArguments().getInt(USER_ID_KEY)
        val currentTab = requireArguments().getString(TAB_KEY)

        val user = User()
        user.id = id32

        if (savedInstanceState == null) {
            vm.fetchUser(id32)
            vm.fetchWinsAndLosses(id32)
        }

        vm.liveUser.observe(this, {
            requireActivity().title = it.profile.personaname
            if (currentTab!! == SEARCH_TAB) {
                (requireActivity() as MainActivity).searchFragmentUserName = it.profile.personaname
            } else if (currentTab == PROFILE_TAB) {
                (requireActivity() as MainActivity).profileFragmentUserName = it.profile.personaname
            }
            user.name = it.profile.personaname
            user.avatarUrl = it.profile.avatarfull
            user.rankId = it.rank_tier
            user.leaderboard_rank = it.leaderboard_rank

            if (user.losses != null) vb.user = user
        })

        vm.liveWinsAndLosses.observe(this, {
            user.wins = it.win
            user.losses = it.lose

            if (user.rankId != null) vb.user = user
            vm.isDataReceived.set(true)
        })

        val listOfFragments: ArrayList<Fragment> =
            arrayListOf(GamesSearchFragment(), MphSearchFragment())

        vb.vpContainerProfileSearch.adapter =
            ViewPagerAdapter(
                listOfFragments,
                this,
                vm.isNeedPositionToStartGames,
                vm.isNeedPositionToStartMph,
                id32
            )

        TabLayoutMediator(vb.tabs, vb.vpContainerProfileSearch,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.vp_games_icon_selector)
                    }
                    1 -> {
                        tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.vp_mph_icon_selector)
                    }
                }
            }).attach()

        val appBar = vb.ablProfileSearch
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == -491) {
                vm.isNeedPositionToStartGames.set(false)
                vm.isNeedPositionToStartMph.set(false)
            }

            mOldVerticalOffset = if (verticalOffset > mOldVerticalOffset) {
                if (vb.vpContainerProfileSearch.currentItem == 0) vm.isNeedPositionToStartMph.set(
                    true
                )
                else vm.isNeedPositionToStartGames.set(true)
                verticalOffset
            } else {
                verticalOffset
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "ProfileSearchFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "ProfileSearchFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "ProfileSearchFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "ProfileSearchFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "ProfileSearchFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "ProfileSearchFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "ProfileSearchFragment. onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyLogs", "ProfileSearchFragment. onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "ProfileSearchFragment. onDestroy")
    }
}