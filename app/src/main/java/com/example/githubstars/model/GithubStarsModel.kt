package com.example.githubstars.model

/**
 * Data class for loading the repository details in the recycler view.
 * TODO convert into database entity for caching the network response
 *
 * @author Piyush Garg
 */
data class GithubStarsModel (val id: Int, val title: String,
                            val description: String, val owner: String,
                            val avatar: String, val stars: Int)
