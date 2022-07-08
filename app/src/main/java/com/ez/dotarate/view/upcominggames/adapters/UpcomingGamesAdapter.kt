package com.ez.dotarate.view.upcominggames.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.databinding.UpcomingGamesItemBinding
import com.ez.domain.model.UpcomingGame

class UpcomingGamesAdapter(
    private val block: (Int) -> Unit
) : PagingDataAdapter<UpcomingGame, UpcomingGamesAdapter.UpcomingGameHolder>(DIFF_CALLBACK) {

    private companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UpcomingGame>() {
            override fun areItemsTheSame(oldItem: UpcomingGame, newItem: UpcomingGame): Boolean {
                return oldItem.begin_at == newItem.begin_at
            }

            override fun areContentsTheSame(oldItem: UpcomingGame, newItem: UpcomingGame): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingGameHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<UpcomingGamesItemBinding>(
            inflater,
            R.layout.upcoming_games_item,
            parent,
            false
        )

        return UpcomingGameHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingGameHolder, position: Int) {
        Log.d("MyLogs", "UpcomingGamesAdapter. fun onBindViewHolder. ПОЗИЦИЯ = $position")
        val upcomingGame = getItem(position)

        if (upcomingGame != null) holder.bind(upcomingGame)
    }

    inner class UpcomingGameHolder(private val binding: UpcomingGamesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(upcomingGame: UpcomingGame) {
            binding.upcomingGame = upcomingGame

            binding.ivUpcomingGamesItemFirstTeam.setOnClickListener {
                block.invoke(upcomingGame.opponents?.get(0)?.opponent?.id ?: 0)
            }

            binding.tvUpcomingGamesItemFirstTeamName.setOnClickListener {
                block.invoke(upcomingGame.opponents?.get(0)?.opponent?.id ?: 0)
            }

            binding.ivUpcomingGamesItemSecondTeam.setOnClickListener {
                block.invoke(upcomingGame.opponents?.get(1)?.opponent?.id ?: 0)
            }

            binding.tvUpcomingGamesItemSecondTeamName.setOnClickListener {
                block.invoke(upcomingGame.opponents?.get(1)?.opponent?.id ?: 0)
            }

            binding.executePendingBindings()
        }
    }
}