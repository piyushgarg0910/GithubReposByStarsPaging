package com.example.githubstars.network

import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class initializes retrofit for communicating with Github api endpoints
 *
 * @author Piyush Garg
 */
class GithubService {

    private val retrofit = Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: GithubApi = retrofit.create(GithubApi::class.java)

}