package com.ez.dotarate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.database.Hero
import com.ez.dotarate.databinding.HeroRecyclerItemBinding

class HeroesAdapter : PagedListAdapter<Hero, HeroesAdapter.HeroesHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hero>() {

            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                // The ID property identifies when items are the same.
                return oldItem.hero_id == newItem.hero_id
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                // Don't use the "==" operator here. Either implement and use .equals(),
                // or write custom userLiveData comparison logic here.
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<HeroRecyclerItemBinding>(
            inflater,
            R.layout.hero_recycler_item,
            parent,
            false
        )
        return HeroesHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesHolder, position: Int) {
        val hero = getItem(position)

        // Note that "hero" can be null if it's a placeholder.
        if (hero != null) holder.bind(hero)
    }

    inner class HeroesHolder(private var binding: HeroRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            // Установка биндинга (передача в него объекта game)
            binding.hero = hero
            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings()
        }
    }
}