package com.example.githubstars.application

import android.app.Application
import com.example.githubstars.network.GithubService
import com.example.githubstars.repo.GithubRepository

/**
 * This is the application class used for initializing services that will be consumed
 * throughout the application lifecycle
 *
 * @author Piyush Garg
 */
class GithubStarsApplication : Application() {

    companion object {
        /**
         * [api] is an instance of retrofit that can be used anywhere in the application
         * to make network calls
         */
        val api = GithubService().api

        /**
         * [repository] is an instance of the {@link GithubRepository} to be used
         * throughout the app
         */
        val repository = GithubRepository()
    }

}