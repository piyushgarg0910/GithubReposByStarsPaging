package com.example.githubstars.viewModel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubstars.application.GithubStarsApplication.Companion.repository
import com.example.githubstars.model.GithubStarsModel
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel class for the GithubFragment
 *
 * @author Piyush Garg
 */
class GithubFragmentViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * This method fetches the Github api items as PagedData, caches it,
     * and returns the data as a flow to be displayed by the recyclerView
     *
     * @return {@link Flow} of {@link PagingData} of the data to be displayed
     * in the recycler view
     */
    fun getRepos() : Flow<PagingData<GithubStarsModel>> {
        return repository.getSearchResults().cachedIn(viewModelScope)
    }

}