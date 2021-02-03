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

class SearchUsersAdapter : PagedListAdapter<SearchUser, SearchUsersAdapter.UserHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchUser>() {
            override fun areItemsTheSame(oldItem: SearchUser, newItem: SearchUser): Boolean {
                return oldItem.account_id == newItem.account_id
            }

            override fun areContentsTheSame(oldItem: SearchUser, newItem: SearchUser): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SearchUserItemBinding>(
            inflater,
            R.layout.search_user_item,
            parent,
            false
        )
        return UserHolder(binding)
    }

    override fun onBindViewHolder(userHolder: UserHolder, position: Int) {
        val user = getItem(position)

        if (user != null) userHolder.bind(user)
    }

    inner class UserHolder(private var binding: SearchUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: SearchUser) {
            binding.user = user
            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings()
        }
    }
}