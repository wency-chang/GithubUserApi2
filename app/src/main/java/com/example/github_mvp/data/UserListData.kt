package com.example.github_mvp.data

import android.os.Parcelable
import com.example.github_mvp.ui.list.ViewHolderType
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserListData(
    @SerializedName("id") val userId: Int,
    @SerializedName("avatar_url") val userImage: String? = null,
    @SerializedName("login") val userAccount: String? = null,
    @SerializedName("site_admin") val isStaff: Boolean = false,
    val type: ViewHolderType = ViewHolderType.User
): Parcelable
