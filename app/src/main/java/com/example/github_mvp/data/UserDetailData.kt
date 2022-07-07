package com.example.github_mvp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailData(
    @SerializedName("id") val userId: Int,
    @SerializedName("login") val userAccount: String,
    @SerializedName("name") val userName: String,
    @SerializedName("avatar_url") val userImage: String?,
    @SerializedName("bio") val userBio: String?,
    @SerializedName("site_admin") val isStaff: Boolean = false,
    @SerializedName("location") val userLocation: String?,
    @SerializedName("blog") val userBlog: String?
): Parcelable {
    val imageWithHttps: String?
        get() = if (userImage.isNullOrEmpty()) null
            else if (userImage.startsWith(HTTPS)) userImage
            else HTTPS + userImage
}

