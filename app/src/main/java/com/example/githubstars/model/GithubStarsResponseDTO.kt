package com.example.githubstars.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object for capturing the github api response from network
 *
 * @author Piyush Garg
 */
data class GithubStarsResponseDTO(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<ItemsResponseModelDTO>)
