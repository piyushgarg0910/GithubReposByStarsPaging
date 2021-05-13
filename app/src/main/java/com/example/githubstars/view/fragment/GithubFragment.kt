package com.example.githubstars.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubstars.R
import com.example.githubstars.databinding.GithubFragmentBinding
import com.example.githubstars.view.adapter.GithubListAdapter
import com.example.githubstars.view.adapter.RepoFooterAdapter
import com.example.githubstars.viewModel.fragment.GithubFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Fragment which presents the recycler view that loads the Github Repo items
 *
 * @author Piyush Garg
 */
class GithubFragment : Fragment(R.layout.github_fragment) {

    private val viewModel : GithubFragmentViewModel by viewModels()
    private var _binding: GithubFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: GithubListAdapter

    companion object {
        private val TAG = GithubFragment::class.java.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GithubFragmentBinding.inflate(inflater, container, false)
        bindViews()
        getRepos()
        return binding.root
    }

    /**
     * This method is used to fetch the data to be displayed in the UI
     */
    private fun getRepos() {
        lifecycleScope.launch {
            viewModel.getRepos().collect {
                itemAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /**
     * This method sets the view fields
     */
    private fun bindViews() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Github Stars"

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        itemAdapter = GithubListAdapter()
        binding.recyclerView.adapter = itemAdapter.withLoadStateHeaderAndFooter(
            header = RepoFooterAdapter { itemAdapter.retry() },
            footer = RepoFooterAdapter { itemAdapter.retry() }
        )

        binding.retryButton.setOnClickListener { itemAdapter.retry() }

        itemAdapter.addLoadStateListener {
            processLoadState(it)
        }
    }

    /**
     * This method is used to show the load states while fetching the paged data to be displayed
     *
     * @param loadStates of the data being queried
     */
    private fun processLoadState(loadStates: CombinedLoadStates) {
        binding.recyclerView.isVisible = loadStates.source.refresh is LoadState.NotLoading
        binding.retryButton.isVisible = loadStates.source.refresh is LoadState.Error
        if (binding.recyclerView.isVisible || binding.retryButton.isVisible) {
            binding.shimmerContainer.showShimmer(false)
            binding.shimmerContainer.isVisible = false
        } else {
            binding.shimmerContainer.showShimmer(true)
            binding.shimmerContainer.isVisible = true
        }

        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error
            ?: loadStates.append as? LoadState.Error
            ?: loadStates.prepend as? LoadState.Error
        errorState?.let { loadStateError ->
            Log.e(TAG, loadStateError.error.localizedMessage, loadStateError.error)
        }
    }
}