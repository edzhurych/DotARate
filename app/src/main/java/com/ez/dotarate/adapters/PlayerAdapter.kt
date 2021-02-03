package com.ez.dotarate.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.collection.ArrayMap
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.constants.PROFILE_TAB
import com.ez.dotarate.constants.SEARCH_TAB
import com.ez.dotarate.constants.TAB_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.PlayerStatsItemBinding
import com.ez.dotarate.model.Player


class PlayerAdapter(
    private val listPlayers: ArrayList<Player>,
    private val maxCountBuff: Int,
    private val maxCountSuppItems: Int,
    private val navController: NavController
) : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<PlayerStatsItemBinding>(
            inflater,
            R.layout.player_stats_item,
            parent,
            false
        )

        return PlayerHolder(binding)
    }

    override fun getItemCount() = listPlayers.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val player = listPlayers[position]

        holder.bind(player)
    }

    inner class PlayerHolder(private var binding: PlayerStatsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.player = player

            if (maxCountBuff == 3) {
                binding.thirdBuffImageView.isVisible = true
                binding.thirdBuffTextView.isVisible = true

            } else if (maxCountBuff == 4) {
                binding.fourthBuffImageView.isVisible = true
                binding.fourthBuffTextView.isVisible = true
            }

            if (maxCountSuppItems == 4) {
                binding.fourthSupportItemCountTextView.isVisible = true
                binding.fourthSupportItemIconImageView.isVisible = true
            } else if (maxCountSuppItems == 5) {
                binding.fifthSupportItemCountTextView.isVisible = true
                binding.fifthSupportItemIconImageView.isVisible = true
            }

            val purchase = player.purchase
            val mapSupportItems: ArrayMap<String, Int> = ArrayMap()

            @Suppress("SENSELESS_COMPARISON")
            if (purchase != null) {
                if (purchase.ward_observer != null) mapSupportItems["a"] =
                    purchase.ward_observer
                if (purchase.ward_sentry != null) mapSupportItems["aa"] =
                    purchase.ward_sentry
                if (purchase.dust != null) mapSupportItems["aaa"] = purchase.dust
                if (purchase.smoke_of_deceit != null) mapSupportItems["aaaa"] =
                    purchase.smoke_of_deceit
                if (purchase.gem != null) mapSupportItems["aaaaa"] = purchase.gem
            }

            var item = 0

            for (i in 0 until mapSupportItems.size) {
                val key = mapSupportItems.keyAt(i)
                var value = mapSupportItems.valueAt(i)

                val drawable = when (key) {
                    "a" -> {
                        item += value * 50
                        R.drawable.observer_ward
                    }
                    "aa" -> {
                        item += value * 75
                        R.drawable.sentry_ward
                    }
                    "aaa" -> {
                        value /= 2
                        item += value * 180
                        R.drawable.dust_of_appearance
                    }
                    "aaaa" -> {
                        item += value * 80
                        R.drawable.smoke_of_deceit
                    }
                    else -> {
                        item += value * 900
                        R.drawable.gem_of_true_sight
                    }
                }

                when (i) {
                    0 -> {
                        binding.firstSupportItemIconImageView.setImageResource(drawable)
                        binding.firstSupportItemCountTextView.text = value.toString()
                        binding.firstSupportItemCountTextView.visibility = View.VISIBLE
                    }
                    1 -> {
                        binding.secondSupportItemIconImageView.setImageResource(drawable)
                        binding.secondSupportItemCountTextView.text = value.toString()
                        binding.secondSupportItemCountTextView.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.thirdSupportItemIconImageView.setImageResource(drawable)
                        binding.thirdSupportItemCountTextView.text = value.toString()
                        binding.thirdSupportItemCountTextView.visibility = View.VISIBLE
                    }
                    3 -> {
                        binding.fourthSupportItemIconImageView.setImageResource(drawable)
                        binding.fourthSupportItemCountTextView.text = value.toString()
                        binding.fourthSupportItemCountTextView.visibility = View.VISIBLE
                    }
                    4 -> {
                        binding.fifthSupportItemIconImageView.setImageResource(drawable)
                        binding.fifthSupportItemCountTextView.text = value.toString()
                        binding.fifthSupportItemCountTextView.visibility = View.VISIBLE
                    }
                }
            }

            binding.suppGold = item

            binding.tvPlayerName.setOnClickListener {
                Log.d("MyLogs", "НАЖАТИЕ НА ИМЯ ИГРОКА = ${player.personaname}")
                val bundle = Bundle()
                bundle.putInt(USER_ID_KEY, player.account_id)
                bundle.putString(TAB_KEY, PROFILE_TAB)

                navController.navigate(
                    R.id.profileSearchFragment,
                    bundle
                )
            }

            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings()
        }
    }
}