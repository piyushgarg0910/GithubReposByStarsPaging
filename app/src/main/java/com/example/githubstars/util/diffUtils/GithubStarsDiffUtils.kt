package com.example.githubstars.util.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.githubstars.model.GithubStarsModel

/**
 * A DiffUtil class for calculating the difference between the Github items to be displayed
 * so that the ListAdapter can be updated accordingly
 *
 * @author Piyush Garg
 */
class GithubStarsDiffUtils : DiffUtil.ItemCallback<GithubStarsModel>() {

    override fun areItemsTheSame(oldItem: GithubStarsModel, newItem: GithubStarsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubStarsModel, newItem: GithubStarsModel): Boolean {
        return ((oldItem.title == newItem.title)
                && (oldItem.description == newItem.description)
                && (oldItem.owner == newItem.owner)
                && (oldItem.stars == newItem.stars))
    }
}