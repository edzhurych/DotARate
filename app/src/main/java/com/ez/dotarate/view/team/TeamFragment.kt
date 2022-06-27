package com.ez.dotarate.view.team

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentTeamBinding
import com.ez.dotarate.view.BaseFragment


class TeamFragment : BaseFragment<TeamViewModel, FragmentTeamBinding>(TeamViewModel::class) {

    private val args by navArgs<TeamFragmentArgs>()

    override fun layout() = R.layout.fragment_team

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        vb.vm = vm
        vm.getTeamById(args.teamId).observe(this) {
            vb.tvTeamName.text = it.name
        }
    }
}