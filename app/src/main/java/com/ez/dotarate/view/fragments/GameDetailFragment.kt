package com.ez.dotarate.view.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.IOnTouchEvent
import com.ez.dotarate.R
import com.ez.dotarate.adapters.PlayerAdapter
import com.ez.dotarate.constants.MATCH_ID_KEY
import com.ez.dotarate.customclasses.DividerItemDecoration
import com.ez.dotarate.customclasses.HScroll
import com.ez.dotarate.customclasses.VScroll
import com.ez.dotarate.databinding.FragmentGameDetailBinding
import com.ez.dotarate.extensions.graphIdToTagMap
import com.ez.domain.model.GameDetail
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.viewmodel.GameDetailViewModel


class GameDetailFragment : BaseFragment<GameDetailViewModel, FragmentGameDetailBinding>(GameDetailViewModel::class),
    IOnTouchEvent {

    private var vScroll: VScroll? = null
    private lateinit var hScroll: HScroll

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        vScroll?.onTouchListener(event!!)
        hScroll.onTouchListener(event!!)

        return true
    }

    private lateinit var recyclerViewRadiant: RecyclerView
    private lateinit var recyclerViewDire: RecyclerView

    @Suppress("SENSELESS_COMPARISON")
    private val gameObserver = Observer<GameDetail> {
        var maxCountBuff = 0
        var maxSupportItems = 0

        if (it != null) {
            vm.isLoaded.set(true)

            // Вычисление максимального количества бафов
            for (player in it.players) {
                val buffs = player.permanent_buffs

                if (buffs != null) {
                    if (maxCountBuff < player.permanent_buffs.size) maxCountBuff =
                        player.permanent_buffs.size
                }

                val purchase = player.purchase
                if (purchase != null) {
                    var currentMaxCountSuppItems = 0
                    if (purchase.dust != null) currentMaxCountSuppItems++
                    if (purchase.smoke_of_deceit != null) currentMaxCountSuppItems++
                    if (purchase.ward_sentry != null) currentMaxCountSuppItems++
                    if (purchase.ward_observer != null) currentMaxCountSuppItems++
                    if (purchase.gem != null) currentMaxCountSuppItems++
                    if (maxSupportItems < currentMaxCountSuppItems) maxSupportItems =
                        currentMaxCountSuppItems
                }
            }

            vb.gameDetail = it

            recyclerViewRadiant.adapter = PlayerAdapter(
                arrayListOf(
                    it.players[0],
                    it.players[1],
                    it.players[2],
                    it.players[3],
                    it.players[4]
                ), maxCountBuff, maxSupportItems, findNavController()
            )

            recyclerViewDire.adapter = PlayerAdapter(
                arrayListOf(
                    it.players[5],
                    it.players[6],
                    it.players[7],
                    it.players[8],
                    it.players[9]
                ), maxCountBuff, maxSupportItems, findNavController()
            )
        }

        when (maxCountBuff) {
            3 -> {
                vb.radiantTitles?.tvRadiantGameDetailBuffs?.setPadding(
                    0,
                    0,
                    resources.getDimension(R.dimen.gameScreenThirdBuffPaddingSize).toInt(),
                    0
                )
                vb.tvDireGameDetailBuffs?.setPadding(
                    0,
                    0,
                    resources.getDimension(R.dimen.gameScreenThirdBuffPaddingSize).toInt(),
                    0
                )
            }
            4 -> {
                vb.tvRadiantGameDetailBuffs?.setPadding(
                    0,
                    0,
                    resources.getDimension(R.dimen.gameScreenFourthBuffPaddingSize).toInt(),
                    0
                )
                vb.tvDireGameDetailBuffs?.setPadding(
                    0,
                    0,
                    resources.getDimension(R.dimen.gameScreenFourthBuffPaddingSize).toInt(),
                    0
                )
            }
        }

        when (maxSupportItems) {
            4 -> {
                Log.d("MyLogs", "МАКСИМАЛЬНОЕ КОЛИЧЕСТВО SUP ITEMOV = $maxSupportItems")
                vb.ivRadiantGameDetailSuppGold?.setPadding(
                    resources.getDimension(R.dimen.gameScreenFourthSuppItemPaddingSize).toInt(),
                    0,
                    0,
                    0
                )
                vb.ivDireGameDetailSuppGold?.setPadding(
                    resources.getDimension(R.dimen.gameScreenFourthSuppItemPaddingSize).toInt(),
                    0,
                    0,
                    0
                )
            }
            5 -> {
                Log.d("MyLogs", "МАКСИМАЛЬНОЕ КОЛИЧЕСТВО SUP ITEMOV = $maxSupportItems")
                vb.ivRadiantGameDetailSuppGold?.setPadding(
                    resources.getDimension(R.dimen.gameScreenFifthSuppItemPaddingSize).toInt(),
                    0,
                    0,
                    0
                )
                vb.ivDireGameDetailSuppGold?.setPadding(
                    resources.getDimension(R.dimen.gameScreenFifthSuppItemPaddingSize).toInt(),
                    0,
                    0,
                    0
                )
            }
        }
    }

    override fun layout() = R.layout.fragment_game_detail

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        // Disable back button
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        val matchId = requireArguments().getLong(MATCH_ID_KEY)
        Log.d("MyLogs", "GamesDetailFragment. AfterCreateView")
        Log.d("MyLogs", "GamesDetailFragment. МАТЧ ID = $matchId")

        activity?.title = ""

        vb.vm = vm

        if (savedInstanceState == null) vm.getGameDetail(matchId)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) vScroll =
            vb.vsvGameDetail
        hScroll = vb.hsvGameDetail

        // Need to set LayoutManager
        recyclerViewRadiant = vb.rvRadiantGameFragment
        recyclerViewRadiant.layoutManager = LinearLayoutManager(activity)
        recyclerViewRadiant.addItemDecoration(
            DividerItemDecoration(
                recyclerViewRadiant.context
            )
        )

        // Need to set LayoutManager
        recyclerViewDire = vb.rvDireGameFragment
        recyclerViewDire.layoutManager = LinearLayoutManager(activity)
        recyclerViewDire.addItemDecoration(
            DividerItemDecoration(
                recyclerViewDire.context
            )
        )

        vm.liveGame.observe(this, gameObserver)

        vm.errorLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            val navHostFragmentProfile =
                requireActivity().supportFragmentManager.findFragmentByTag(graphIdToTagMap.valueAt(2)) as NavHostFragment
            navHostFragmentProfile.navController.popBackStack()
            requireActivity().title = (activity as MainActivity).userName
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "GamesDetailFragment. onStart")
        val hsv = vb.hsvGameDetail
        hsv.setOnScrollChangeListener { _, scrollX, _, _, _ ->
            run {
                if (scrollX >= 523) {
                    vb.vGameDetailFirst.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailSecond.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailThird.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailFourth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailFifth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailSixth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailSeventh.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailEighth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailNinth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                    vb.vGameDetailTenth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorShadow
                        )
                    )
                } else {
                    vb.vGameDetailFirst.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailSecond.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailThird.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailFourth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailFifth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailSixth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailSeventh.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailEighth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailNinth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                    vb.vGameDetailTenth.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteLight
                        )
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "GamesDetailFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "GamesDetailFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "GamesDetailFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "GamesDetailFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "GamesDetailFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "GamesDetailFragment. onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyLogs", "GamesDetailFragment. onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        // parentActivity.active = parentActivity.gamesFragment
        Log.d("MyLogs", "GamesDetailFragment. onDestroy")
    }
}