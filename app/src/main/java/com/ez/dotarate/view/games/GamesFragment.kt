package com.ez.dotarate.view.games

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.domain.model.Game
import com.ez.dotarate.R
import com.ez.dotarate.adapters.GamesAdapter
import com.ez.dotarate.constants.CONVERTER_NUMBER
import com.ez.dotarate.constants.MATCH_ID_KEY
import com.ez.dotarate.constants.REFRESH_OBSERVABLE_BOOLEAN_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentGamesBinding
import com.ez.dotarate.listeners.ClickListener
import com.ez.dotarate.listeners.RecyclerTouchListener
import com.ez.dotarate.view.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class GamesFragment : BaseFragment<GamesViewModel, FragmentGamesBinding>(GamesViewModel::class) {

    val adapter: GamesAdapter by inject()

    private lateinit var pagedList: List<Game>

    override fun layout() = R.layout.fragment_games

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        vm.isLocal = true

        val isNeedPositionToStart =
            requireArguments().getSerializable(REFRESH_OBSERVABLE_BOOLEAN_KEY) as ObservableBoolean

        Log.d("MyLogs", "GamesFragment. AfterCreateView")

        vb.adapter = adapter
        vb.vm = vm
        vb.isNeedPositionToStart = isNeedPositionToStart
        vb.isDataReceived = vm.isDataReceived

        val id32: Int =
            (requireActivity().intent!!.getLongExtra(USER_ID_KEY, 0) - CONVERTER_NUMBER).toInt()
        vm.id32 = id32

        // Need to set LayoutManager
        val recyclerView = vb.rvGamesFragment
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager

        // Set Touch Listener for RecyclerView
        recyclerView.addOnItemTouchListener(
            RecyclerTouchListener(
                requireContext(),
                recyclerView,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        try {
                            val game = pagedList[position]

                            val bundle = Bundle()
                            bundle.putLong(MATCH_ID_KEY, game!!.match_id)
                            if (findNavController().currentDestination?.id == R.id.profileFragment) {
                                Log.d(
                                    "MyLogs",
                                    "games Fragment. КОНТРОЛЕР = ${findNavController()}"
                                )
                                Log.d(
                                    "MyLogs",
                                    "games Fragment. id ТЕКУЩЕГО ФРАГМЕНТА = ${findNavController().currentDestination?.id}"
                                )
                                findNavController().navigate(
                                    R.id.action_profileFragment_to_gameDetailFragment,
                                    bundle
                                )
                            } else {
                                Log.d(
                                    "MyLogs",
                                    "games Fragment. КОНТРОЛЕР = ${findNavController()}"
                                )
                                Log.d(
                                    "MyLogs",
                                    "games Fragment. id ТЕКУЩЕГО ФРАГМЕНТА = ${findNavController().currentDestination?.id}"
                                )
                            }
                        } catch (e: NullPointerException) {
                            Toast.makeText(activity, "Пустые данные", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )

        if (savedInstanceState == null) vm.getGames(id32)

        // LiveData<PagedList<Game>> subscriber
        vm.liveGame.observe(this) {
            Log.d("MyLogs", "GamesFragment.  LiveData с PagedList")
            val games = adapter.snapshot().items

            if (games != null && games.isNotEmpty()) {
                vm.isGamesEmpty.set(false)
                Log.d("MyLogs", "GamesFragment. PagedList = $it")
                pagedList = games
            } else {
                vm.isGamesEmpty.set(true)
                Log.d("MyLogs", "GamesFragment. PagedList пустой = $it")
            }
            // Need to use submitList to set the PagedListAdapter value
            vm.viewModelScope.launch { adapter.submitData(it) }
            vm.isDataReceived.set(true)
        }

//        vm.errorLiveData.observe(this, Observer {
//            it.getContentIfNotHandled()?.let { its ->
//                // Only proceed if the event has never been handled
//                Toast.makeText(activity, its, Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "GamesFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "GamesFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "GamesFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "GamesFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "GamesFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "GamesFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "GamesFragment. onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyLogs", "GamesFragment. onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "GamesFragment. onDestroy")
    }
}