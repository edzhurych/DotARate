package com.ez.dotarate.view.team

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ez.domain.model.Team
import com.ez.domain.model.TeamPlayer
import com.ez.domain.repository.PandaScoreRepository
import com.ez.dotarate.BR
import com.ez.dotarate.R
import com.ez.dotarate.view.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding


class TeamViewModel(
    application: Application,
    private val repository: PandaScoreRepository
) : BaseViewModel(application) {

    val itemBinding = ItemBinding.of<TeamPlayer>(BR.teamPlayer, R.layout.team_player_item)

    val items = ObservableArrayList<TeamPlayer>()

    fun getTeamById(teamId: Int): LiveData<Team> = liveData {
        val team = repository.fetchTeamById(teamId)
        team.players?.let { items.addAll(it) }
        emit(team)
    }
}