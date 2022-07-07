package com.example.github_mvp.ui.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.insertHeaderItem
import com.example.github_mvp.data.UserDetailData
import com.example.github_mvp.data.UserListData
import com.example.github_mvp.service.GithubWebClient
import com.example.github_mvp.ui.list.ViewHolderType
import com.example.github_mvp.ui.paging.UserListPagingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 20
class GithubUserRepository(private val service: GithubWebClient) {

    suspend fun getUserList(since: Int = 0, limit: Int = PAGE_SIZE): List<UserListData> {
        return service.getUserList(since, limit)
    }

    suspend fun getUserDetail(account: String): UserDetailData {
        return service.getUserDetail(account)
    }

    fun getPagingUserList(): Flow<PagingData<UserListData>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE, PAGE_SIZE*2),
            pagingSourceFactory = {
                UserListPagingDataSource(this)
            }
        ).flow
            .map { data ->
                data.insertHeaderItem(item = UserListData(0, type = ViewHolderType.Header))
            }
    }
}