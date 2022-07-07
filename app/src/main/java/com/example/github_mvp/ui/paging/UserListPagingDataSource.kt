package com.example.github_mvp.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.github_mvp.data.UserListData
import com.example.github_mvp.ui.repository.GithubUserRepository

class UserListPagingDataSource(private val repo: GithubUserRepository): PagingSource<Int, UserListData>() {
    private val limitation: Int = 100
    override fun getRefreshKey(state: PagingState<Int, UserListData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
                ?: state.closestPageToPosition(it)?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserListData> {
        val key = params.key ?: 0
        return try {
            val response = repo.getUserList(key)
            val pre = key - response.size
            val prevKey = when {
                (pre < 0) -> null
                else -> pre
            }
            val lastKey = response.lastOrNull()?.userId
            val nextKey = if (lastKey != null && lastKey > limitation) null else lastKey
            LoadResult.Page(
                response,
                prevKey,
                nextKey
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}