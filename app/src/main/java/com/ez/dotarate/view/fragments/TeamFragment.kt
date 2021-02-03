package com.ez.dotarate.view.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentTeamBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.TeamViewModel


class TeamFragment : BaseFragment<TeamViewModel, FragmentTeamBinding>() {

    private val args by navArgs<TeamFragmentArgs>()

    override fun layout() = R.layout.fragment_team

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        vb.vm = vm
        vm.getTeamById(args.teamId).observe(this, {
            vb.tvTeamName.text = it.name
        })
    }
}