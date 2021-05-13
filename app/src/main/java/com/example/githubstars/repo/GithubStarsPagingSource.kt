package com.example.githubstars.repo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubstars.application.GithubStarsApplication.Companion.api
import com.example.githubstars.model.GithubStarsModel
import com.example.githubstars.repo.GithubRepository.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

/**
 * This class defines the Github Paging source and fetches the Github repo data from network
 *
 * @author Piyush Garg
 */
class GithubStarsPagingSource : PagingSource<Int, GithubStarsModel>() {

    companion object {
        private val TAG = GithubStarsPagingSource::class.java.simpleName
    }

    override fun getRefreshKey(state: PagingState<Int, GithubStarsModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubStarsModel> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX

        return try {
            val itemsResponse = api.getTopRepositoriesByPage(position)
            val listOfItems = ArrayList<GithubStarsModel>()
            if (itemsResponse.items.isNotEmpty()) {
                itemsResponse.items.forEach { item ->
                    listOfItems.add(GithubStarsModel(item.id, item.name, item.description, item.owner.login, item.owner.avatarUrl, item.stargazersCount))
                }
            }
            LoadResult.Page(
                data = listOfItems,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (position == 10) null else position + (params.loadSize / NETWORK_PAGE_SIZE)
            )
        } catch (exception: IOException) {
            Log.e(TAG, exception.localizedMessage, exception)
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e(TAG, exception.localizedMessage, exception)
            return LoadResult.Error(exception)
        }
    }
}