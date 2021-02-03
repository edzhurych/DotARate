package com.ez.dotarate.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.database.Game
import com.ez.dotarate.databinding.GameListItemBinding

class GamesHolder(private var binding: GameListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game?) {
        // Установка биндинга (передача в него объекта game)
        binding.game = game
        // Используется для того, что бы биндинг выполинлся как можно скорее
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): GamesHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<GameListItemBinding>(
                inflater,
                R.layout.game_list_item,
                parent,
                false
            )
            return GamesHolder(binding)
        }
    }

//    fun updateScore(item: GamesHolder?) {
//        post = item
//        score.text = "${item?.score ?: 0}"
//    }
}