package com.example.github_mvp.service

import com.example.github_mvp.data.UserDetailData
import com.example.github_mvp.data.UserListData

interface GithubWebClient {
    suspend fun getUserList(since: Int, limit: Int): List<UserListData>
    suspend fun getUserDetail(account: String): UserDetailData
}