package com.example.githubstars.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.githubstars.databinding.ItemRowBinding
import com.example.githubstars.model.GithubStarsModel
import com.example.githubstars.util.diffUtils.GithubStarsDiffUtils
import com.example.githubstars.view.viewHolder.ItemViewHolder

/**
 * Adapter for the recycler view that shows the github repo items
 *
 * @author Piyush Garg
 */
class GithubListAdapter : PagingDataAdapter<GithubStarsModel, ItemViewHolder>(GithubStarsDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

}