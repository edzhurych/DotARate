package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.domain.model.Game
import com.ez.dotarate.R
import com.ez.dotarate.adapters.GamesAdapter
import com.ez.dotarate.constants.MATCH_ID_KEY
import com.ez.dotarate.constants.REFRESH_OBSERVABLE_BOOLEAN_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentGamesBinding
import com.ez.dotarate.listeners.ClickListener
import com.ez.dotarate.listeners.RecyclerTouchListener
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewmodel.GamesViewModel


class GamesSearchFragment :
    BaseFragment<GamesViewModel, FragmentGamesBinding>(GamesViewModel::class) {

    private val adapter = GamesAdapter()

    private lateinit var pagedList: PagedList<Game>

    override fun layout() = R.layout.fragment_games

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "GamesSearchFragment. AfterCreateView")

        val id32 = requireArguments().getInt(USER_ID_KEY)
        Log.d("MyLogs", "GamesSearchFragment. id32 = $id32")
        val isNeedPositionToStart =
            requireArguments().getSerializable(REFRESH_OBSERVABLE_BOOLEAN_KEY) as ObservableBoolean

        vb.adapter = adapter
        vb.vm = vm
        vb.isNeedPositionToStart = isNeedPositionToStart
        vb.isDataReceived = vm.isDataReceived

        vm.id32 = id32
        vm.isLocal = false

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

                            findNavController().navigate(
                                R.id.gameDetailFragment,
                                bundle
                            )

                        } catch (e: NullPointerException) {
                            Toast.makeText(activity, "Пустые данные", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )

        // LiveData<PagedList<Game>> subscriber
        vm.liveGame.observe(this, {
            Log.d("MyLogs", "GamesSearchFragment.  LiveData с PagedList")
            if (it != null && it.size > 0) {
                vm.isGamesEmpty.set(false)
                Log.d("MyLogs", "GamesSearchFragment. PagedList = $it")
            } else {
                vm.isGamesEmpty.set(true)
                Log.d("MyLogs", "GamesSearchFragment. PagedList ПУСТОЙ = $it")
            }
            pagedList = it
            // Need to use submitList to set the PagedListAdapter value
            adapter.submitList(it)
            vm.isDataReceived.set(true)
            // DataSource возвращает пустой список, поэтому добавляем этот Callback
            it.addWeakCallback(null, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {
                    Log.d("MyLogs", "GamesSearchFragment. onChanged")
                }

                override fun onInserted(position: Int, count: Int) {
                    Log.d("MyLogs", "GamesSearchFragment. onInserted")
                    vm.isGamesEmpty.set(false)
                }

                override fun onRemoved(position: Int, count: Int) {
                    Log.d("MyLogs", "GamesSearchFragment. onInserted")
                }
            })
            Log.d(
                "MyLogs",
                "GamesSearchFragment. ЗНАЧЕНИЕ isDataReceived = ${vm.isDataReceived.get()}"
            )
        })


    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "GamesSearchFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "GamesSearchFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "GamesSearchFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "GamesSearchFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "GamesSearchFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "GamesSearchFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "GamesSearchFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "GamesSearchFragment. onDestroy")
    }
}