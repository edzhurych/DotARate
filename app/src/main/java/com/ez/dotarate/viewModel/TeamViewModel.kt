package com.ez.dotarate.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ez.dotarate.BR
import com.ez.dotarate.R
import com.ez.domain.model.Team
import com.ez.domain.model.TeamPlayer
import com.ez.domain.repository.PandaScoreRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

class TeamViewModel @Inject constructor(
    private val repository: PandaScoreRepository
) : ViewModel() {

    val itemBinding = ItemBinding.of<TeamPlayer>(BR.teamPlayer, R.layout.team_player_item)

    val items = ObservableArrayList<TeamPlayer>()

    fun getTeamById(teamId: Int): LiveData<Team> = liveData {
        val team = repository.fetchTeamById(teamId)
        team.players?.let { items.addAll(it) }
        emit(team)
    }
}