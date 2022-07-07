package com.example.github_mvp.service

import com.example.github_mvp.data.UserDetailData
import com.example.github_mvp.data.UserListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    suspend fun getUserList(
        @Query("since") id: Int,
        @Query("per_page") num: Int
    ): Response<List<UserListData>>

    @GET("users/{userAccount}")
    suspend fun getUserDetail(@Path("userAccount") account: String): Response<UserDetailData>
}