package com.example.githubstars.network

import com.example.githubstars.model.GithubStarsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This class defines the methods to interact with github api endpoints
 *
 * @author Piyush Garg
 */
interface GithubApi {

    /**
     * This method is used to get the list of starred repos on the given page,
     * sorted by stargazers count in descending order, 10 items at a time.
     *
     * @param pageNumber of the page to be fetched
     * @return {@link GithubStarsResponseDTO} containing 10 items at the give [pageNumber]
     */
    @GET("repositories?q=stars:>0&sort=stars&order=desc&per_page=10")
    suspend fun getTopRepositoriesByPage(@Query("page") pageNumber : Int):
            GithubStarsResponseDTO
    
}