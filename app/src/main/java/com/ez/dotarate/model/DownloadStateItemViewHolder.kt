package com.ez.dotarate.model

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.ez.dotarate.R
import com.ez.dotarate.model.repository.DownloadState
import com.ez.dotarate.model.repository.Status

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class DownloadStateItemViewHolder(view: View)
    : RecyclerView.ViewHolder(view) {
    private val progressBar = view.findViewById<ProgressBar>(R.id.pbStateItem)
//    private val retry = view.findViewById<Button>(R.id.retry_button)
//    private val errorMsg = view.findViewById<TextView>(R.id.error_msg)
//    init {
//        retry.setOnClickListener {
//            retryCallback()
//        }
//    }
    fun bindTo(downloadState: DownloadState?) {
        progressBar.visibility = toVisibility(downloadState?.status == Status.RUNNING)
//        retry.visibility = toVisibility(downloadState?.status == Status.FAILED)
//        errorMsg.visibility = toVisibility(downloadState?.msg != null)
//        errorMsg.text = downloadState?.msg
    }

    companion object {
        fun create(parent: ViewGroup): DownloadStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.download_state_item, parent, false)
            return DownloadStateItemViewHolder(view)
        }

        fun toVisibility(constraint : Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}