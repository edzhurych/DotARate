package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.dotarate.R
import com.ez.dotarate.adapters.TopPlayersAdapter
import com.ez.dotarate.constants.SEARCH_TAB
import com.ez.dotarate.constants.TAB_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentSearchBinding
import com.ez.dotarate.listeners.ClickListener
import com.ez.dotarate.listeners.RecyclerTouchListener
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewmodel.SearchViewModel

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(SearchViewModel::class) {

    lateinit var adapter: TopPlayersAdapter

    override fun layout() = R.layout.fragment_search

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "SearchFragment. afterCreateView")

        val recyclerView = vb.rvFragmentSearch
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.addOnItemTouchListener(
            RecyclerTouchListener(requireContext(),
                recyclerView,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        val currentUser = vm.liveTopPlayers.value!![position]

                        val bundle = Bundle()
                        bundle.putInt(USER_ID_KEY, currentUser.account_id)
                        bundle.putString(TAB_KEY, SEARCH_TAB)

                        findNavController().navigate(R.id.profileSearchFragment, bundle)
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }

                })
        )

        vb.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.searchUsersFragment)
        }

        if (!vm.isExistedFragment) {
            Log.d("MyLogs", "SearchFragment. isExistedFragment = ${vm.isExistedFragment}")
            vm.getTopPlayers()
        }

        vm.liveTopPlayers.observe(this, {
            adapter = TopPlayersAdapter(it)
            vb.adapter = adapter
        })

        vm.isExistedFragment = true
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "SearchFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "SearchFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "SearchFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "SearchFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "SearchFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "SearchFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "SearchFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.isExistedFragment = false
        Log.d("MyLogs", "SearchFragment. onDestroy")
    }
}