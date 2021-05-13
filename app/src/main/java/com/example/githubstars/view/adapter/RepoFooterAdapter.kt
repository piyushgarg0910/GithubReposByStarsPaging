package com.example.githubstars.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.githubstars.databinding.RecyclerViewFooterBinding
import com.example.githubstars.view.viewHolder.RepoFooterViewHolder

/**
 * Adapter for displaying the footer while fetching the paged data from the network
 *
 * @author Piyush Garg
 */
class RepoFooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<RepoFooterViewHolder>() {
    override fun onBindViewHolder(holder: RepoFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): RepoFooterViewHolder {
        val binding = RecyclerViewFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoFooterViewHolder(binding, retry)
    }
}