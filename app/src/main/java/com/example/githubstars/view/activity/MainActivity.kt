package com.example.githubstars.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.githubstars.R
import com.example.githubstars.view.fragment.GithubFragment

/**
 * Activity class which hosts the Github Fragment
 *
 * @author Piyush Garg
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.mainContainer, GithubFragment())
        }
    }
}