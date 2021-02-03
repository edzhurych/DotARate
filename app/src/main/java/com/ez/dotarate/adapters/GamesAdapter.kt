package com.ez.dotarate.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.database.Game
import com.ez.dotarate.model.DownloadStateItemViewHolder
import com.ez.dotarate.model.GamesHolder
import com.ez.dotarate.model.repository.DownloadState

class GamesAdapter : PagedListAdapter<Game, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Game>() {

            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                // The ID property identifies when items are the same.
                return oldItem.match_id == newItem.match_id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                // Don't use the "==" operator here. Either implement and use .equals(),
                // or write custom userLiveData comparison logic here.
                return oldItem == newItem
            }
        }
    }

    private var downloadState: DownloadState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return when (viewType) {
            R.layout.game_list_item -> {
                Log.d("MyLogs", "АДАПТЕР. onCreateViewHolder ДЛЯ ИГРЫ")
                GamesHolder.create(parent)
            }
            R.layout.download_state_item -> {
                Log.d("MyLogs", "АДАПТЕР. onCreateViewHolder ДЛЯ ProgressBar'a")
                DownloadStateItemViewHolder.create(parent)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.game_list_item -> (holder as GamesHolder).bind(getItem(position))
            R.layout.download_state_item -> (holder as DownloadStateItemViewHolder).bindTo(
                downloadState)
        }
    }

    private fun hasExtraRow() = downloadState != null && downloadState != DownloadState.LOADED

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {

        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.download_state_item
        } else {
            R.layout.game_list_item
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setDownloadState(newDownloadState: DownloadState?) {
        val previousState = this.downloadState
        val hadExtraRow = hasExtraRow()
        this.downloadState = newDownloadState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newDownloadState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}