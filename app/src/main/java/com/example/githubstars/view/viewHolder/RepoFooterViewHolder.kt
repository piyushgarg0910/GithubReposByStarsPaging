package com.example.githubstars.view.viewHolder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.githubstars.databinding.RecyclerViewFooterBinding

/**
 * View holder that shows the loading status of the data. It shows placeholder shimmers,
 * error messages, and retry button in case of load failure.
 *
 * @author Piyush Garg
 */
class RepoFooterViewHolder(private val binding: RecyclerViewFooterBinding, retry: () -> Unit)
    : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButtonFooter.setOnClickListener { retry.invoke() }
    }

    /**
     * This method binds the load state of the data to the view
     *
     * @param loadState of the data being fetched over the network
     */
    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsgFooter.text = loadState.error.localizedMessage
        }
        binding.retryButtonFooter.isVisible = loadState is LoadState.Error
        binding.errorMsgFooter.isVisible = loadState is LoadState.Error
        if (binding.retryButtonFooter.isVisible || binding.errorMsgFooter.isVisible) {
            binding.shimmerContainerFooter.showShimmer(false)
            binding.shimmerContainerFooter.isVisible = false
        } else {
            binding.shimmerContainerFooter.showShimmer(true)
            binding.shimmerContainerFooter.isVisible = true
        }
    }
}