package com.ez.dotarate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.database.SearchUser
import com.ez.dotarate.databinding.SearchUserItemBinding

class TopPlayersAdapter(private val listTopPlayers: ArrayList<SearchUser>) :
    RecyclerView.Adapter<TopPlayersAdapter.TopPlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPlayerHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SearchUserItemBinding>(
            inflater,
            R.layout.search_user_item,
            parent,
            false
        )

        return TopPlayerHolder(binding)
    }

    override fun onBindViewHolder(holder: TopPlayerHolder, position: Int) {
        val user = listTopPlayers[position]

        holder.bind(user)
    }

    inner class TopPlayerHolder(private var binding: SearchUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: SearchUser) {
            binding.user = user
            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = listTopPlayers.size
}