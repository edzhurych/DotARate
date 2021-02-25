package com.ez.dotarate.view.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.ez.domain.model.User
import com.ez.dotarate.R
import com.ez.dotarate.adapters.ViewPagerAdapter
import com.ez.dotarate.constants.CONVERTER_NUMBER
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentProfileBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.StartActivity
import com.ez.dotarate.viewmodel.ProfileViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(ProfileViewModel::class) {

    private var mOldVerticalOffset = 0

    override fun layout() = R.layout.fragment_profile

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "ProfileFragment. CreateView")

        val id32 = (requireActivity().intent.getLongExtra(USER_ID_KEY, 0) - CONVERTER_NUMBER).toInt()

        // Говорим фрагменту, что ему нужно отобразить меню
        setHasOptionsMenu(true)

        vb.vm = vm

        val user = User()
        user.id = id32

//        swipeRefreshLayout = vb.srlProfile
//        swipeRefreshLayout.setOnRefreshListener {
//            // TODO: REST запросы
//            swipeRefreshLayout.isRefreshing = false
//        }

        vm.liveUser.observe(this, {
            Log.d("MyLogs", "ProfileFragment. liveUser.")
            it?.let {
                Log.d("MyLogs", "ProfileFragment. liveUser. УСТАНОВКА isDataReceived в true")
                vm.isDataReceived.set(true)
                vb.user = it
            }
        })

        if (savedInstanceState == null) {
            vm.getUserResponse(id32)
            vm.getWinsAndLosses(id32)
        }

        vm.userResponseLiveData.observe(this, {
            Log.d("MyLogs", "ProfileFragment. userResponseLiveData.")

            user.name = it.profile.personaname
            user.avatarUrl = it.profile.avatarfull
            user.rankId = it.rank_tier
            user.leaderboard_rank = it.leaderboard_rank

            if (user.wins != null) vm.saveUser(user)
        })

        vm.liveWinsAndLosses.observe(this, {
            Log.d("MyLogs", "ProfileFragment. wlLiveData.")

            user.wins = it.win
            user.losses = it.lose

            if (user.avatarUrl != null) vm.saveUser(user)
        })

        val listOfFragments: ArrayList<Fragment> = arrayListOf(GamesFragment(), MphFragment())

        vb.vpContainer.adapter =
            ViewPagerAdapter(listOfFragments, this, vm.isNeedPositionToStartGames, vm.isNeedPositionToStartMph, id32)
        Log.d("MyLogs", "****** ПЕРВОЕ VIEW во ViewPager = ${vb.vpContainer[0]}")

        TabLayoutMediator(vb.tabs, vb.vpContainer,
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

        val appBar = vb.ablProfile
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == -491) {
                vm.isNeedPositionToStartGames.set(false)
                vm.isNeedPositionToStartMph.set(false)
            }

            if (verticalOffset > mOldVerticalOffset) {
                if (vb.vpContainer.currentItem == 0) vm.isNeedPositionToStartMph.set(true)
                else vm.isNeedPositionToStartGames.set(true)
                mOldVerticalOffset = verticalOffset
            } else {
                mOldVerticalOffset = verticalOffset
            }
            Log.d("MyLogs", "**********  ЗНАЧЕНИЕ verticalOffset = $verticalOffset")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.proileLogout -> {
                context
                if (context != null) {
                    val builder = AlertDialog.Builder(requireContext())

                    builder.setMessage(R.string.profile_screen_dialog_logout)
                    builder.setPositiveButton(R.string.yes) { _: DialogInterface, id: Int ->
                        vm.logout()
                        val intent = Intent(activity, StartActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }

                    builder.setNegativeButton(R.string.no) { dialog: DialogInterface, id: Int -> dialog.dismiss() }

                    val alert = builder.create()
                    alert.show()
                }
            }
        }

        return true
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "ProfileFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "ProfileFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "ProfileFragment. onCreate.    ---    bundle = $savedInstanceState")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "ProfileFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "ProfileFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "ProfileFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "ProfileFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "ProfileFragment. onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MyLogs", "ProfileFragment. onSaveInstanceState")
    }
}