package com.example.githubstars.model

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object for capturing license data from the items field
 * in github api response from network
 *
 * @author Piyush Garg
 */
data class LicenseResponseModelDTO (
    @SerializedName ("key") val key: String,
    @SerializedName ("name") val name: String,
    @SerializedName ("spdx_id") val spdxId: String,
    @SerializedName ("url") val licenseUrl: String,
    @SerializedName ("node_id") val licenseNodeId: String)