package com.example.githubstars.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubstars.model.GithubStarsModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that defines how the data is fetched and converts the
 * network response into a {@link Flow}
 * TODO implement caching into database and selection of source
 *      to provide the data to be displayed (database vs network)
 *
 * @author Piyush Garg
 */
class GithubRepository {

    companion object {
        /**
         * Constant indicating the size of items array on each page of the Github
         * api response
         */
        const val NETWORK_PAGE_SIZE = 10
    }

    /**
     * This class defines the pager data and returns the paged data from github api
     * as a flow
     *
     * @return a {@link Flow} of {@link PagingData} of the response from the network
     */
    fun getSearchResults() : Flow<PagingData<GithubStarsModel>> {
        return Pager(
            PagingConfig(
                NETWORK_PAGE_SIZE,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubStarsPagingSource() } ).flow
    }
}