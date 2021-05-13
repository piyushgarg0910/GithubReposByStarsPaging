package com.example.githubstars.view.viewHolder

import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.recyclerview.widget.RecyclerView
import com.example.githubstars.R
import com.example.githubstars.databinding.ItemRowBinding
import com.example.githubstars.model.GithubStarsModel
import com.squareup.picasso.Picasso

/**
 * View holder that shows the github item data for each row of the recycler view
 *
 * @author Piyush Garg
 */
class ItemViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    /**
     * This method binds the data to the view elements
     *
     * @param item entry for the row to be displayed in the viewHolder
     */
    fun bind(item: GithubStarsModel) {
        binding.repoName.text = item.title
        binding.repoDescription.text = item.description
        binding.repoOwner.text = item.owner
        binding.repoStarsCount.text = item.stars.toString()

        binding.repoOwnerAvatar.viewTreeObserver
            .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.repoOwnerAvatar.viewTreeObserver
                        .removeOnGlobalLayoutListener(this)

                    Picasso.get()
                        .load(item.avatar)
                        .resize(0, binding.repoOwnerAvatar.height)
                        .placeholder(R.drawable.avatar_placeholder)
                        .error(R.drawable.avatar_placeholder)
                        .into(binding.repoOwnerAvatar)
                }
            })
    }
}