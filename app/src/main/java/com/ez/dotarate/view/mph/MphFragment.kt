package com.ez.dotarate.view.mph

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.dotarate.R
import com.ez.dotarate.adapters.HeroesAdapter
import com.ez.dotarate.constants.CONVERTER_NUMBER
import com.ez.dotarate.constants.REFRESH_OBSERVABLE_BOOLEAN_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentMphBinding
import com.ez.dotarate.view.BaseFragment

class MphFragment : BaseFragment<MphViewModel, FragmentMphBinding>(MphViewModel::class) {
    private val adapter = HeroesAdapter()

    override fun layout() = R.layout.fragment_mph

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        vm.isLocal = true
        Log.d("MyLogs", "MphFragment. AfterCreateView")

        val id32: Int =
            (requireActivity().intent!!.getLongExtra(USER_ID_KEY, 0) - CONVERTER_NUMBER).toInt()

        val isNeedPositionToStart =
            requireArguments().getSerializable(REFRESH_OBSERVABLE_BOOLEAN_KEY) as ObservableBoolean

        vb.isNeedPositionToStart = isNeedPositionToStart
        vb.adapter = adapter
        vb.isDataReceivedMph = vm.isDataReceivedMph

        if (savedInstanceState == null) vm.getHeroes(id32)

        // Need to set LayoutManager
        val recyclerView = vb.rvMphFragment
        recyclerView.layoutManager = LinearLayoutManager(activity)

        vm.liveHeroes.observe(this) {
            if (it != null && it.size > 0) {
                vm.isHeroesEmpty.set(false)
            } else vm.isHeroesEmpty.set(true)
            // Need to use submitList to set the PagedListAdapter value
            adapter.submitList(it)

            vm.isDataReceivedMph.set(true)
            Log.d("MyLogs", "MphFragment. Observer change = ${vm.isDataReceivedMph.get()}")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "MphFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "MphFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "MphFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "MphFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "MphFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "MphFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "MphFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "MphFragment. onDestroy")
    }
}