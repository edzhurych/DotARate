package com.ez.dotarate.view.upcominggames

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.ez.dotarate.Log as log
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.domain.model.UpcomingGame
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentUpcomingGamesBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.upcominggames.adapters.CategoryUGAdapter
import com.ez.dotarate.view.upcominggames.adapters.UpcomingGamesAdapter


class UpcomingGamesFragment : BaseFragment<UpcomingGamesViewModel, FragmentUpcomingGamesBinding>(
    UpcomingGamesViewModel::class
) {

    private val adapter: UpcomingGamesAdapter by lazy {
        UpcomingGamesAdapter {
            findNavController().navigate(
                UpcomingGamesFragmentDirections.actionUpcomingGamesFragmentToTeamFragment(
                    it
                )
            )
        }
    }

    private lateinit var pagedListUpcomingGames: PagedList<UpcomingGame>

    private val observer: (PagedList<UpcomingGame>) -> Unit = {
        log.d("UpcomingGamesFragment. Подписчик livedata. Значение = $it")
        pagedListUpcomingGames = it
        adapter.submitList(it)

        val categories = if (it.isNotEmpty()) mutableMapOf(Pair(0, "All")) else mutableMapOf() // TODO: Change to res
        categories.putAll(it.toList().associate { upcomingGame ->
            upcomingGame.league.id to upcomingGame.league.name
        })
        vm.categories.value = categories

        if (it.isNotEmpty()) vm.isDataLoaded.set(true)
    }

    override fun layout() = R.layout.fragment_upcoming_games

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        val categoryHorizontalManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        vb.rvCategory.layoutManager = categoryHorizontalManager

        vb.adapter = adapter
        vb.isDataLoaded = vm.isDataLoaded

        vm.liveUpcomingGames.observe(this, observer)

        vm.categories.observe(this) {
            vb.rvCategory.adapter = CategoryUGAdapter(it) { leagueId ->
                log.d("UpcomingGamesFragment. Click on the $leagueId category")
                if (vm.liveLeagueId.value != leagueId) {
                    vm.liveUpcomingGames.removeObserver(observer)
                    vm.liveLeagueId.value = leagueId

                    vm.liveUpcomingGames.observe(this, observer)
                }

            }
        }

        log.d("UpcomingGamesFragment. AfterCreateView")
    }

    private fun twitchIsInstalled(): Boolean {
        val packageName = "tv.twitch.android.app"
        return try {
            requireContext().packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_ACTIVITIES
            )
            true
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    private fun loadTwitch() {
        val packageName = "tv.twitch.android.app"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=$packageName")
        requireContext().startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        log.d("UpcomingGamesFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        log.d("UpcomingGamesFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log.d("UpcomingGamesFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log.d("UpcomingGamesFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        log.d("UpcomingGamesFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        log.d("UpcomingGamesFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        log.d("UpcomingGamesFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log.d("UpcomingGamesFragment. onDestroy")
    }
}