package com.example.github_mvp.service

import com.example.github_mvp.data.UserDetailData
import com.example.github_mvp.data.UserListData
import com.example.github_mvp.utils.getBody

object GithubWebClientImp: GithubWebClient {
    private lateinit var service: GithubService

    fun initService(service: GithubService) {
        this.service = service
    }

    override suspend fun getUserList(since: Int, limit: Int): List<UserListData> {
        return service.getUserList(since, limit).getBody()
    }

    override suspend fun getUserDetail(account: String): UserDetailData {
        return service.getUserDetail(account).getBody()
    }
}