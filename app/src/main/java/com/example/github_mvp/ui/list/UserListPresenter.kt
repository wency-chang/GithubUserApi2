package com.example.github_mvp.ui.list

import androidx.paging.PagingData
import com.example.github_mvp.data.UserListData
import com.example.github_mvp.ui.CoroutinePresenter
import com.example.github_mvp.ui.contract.UserListContract
import com.example.github_mvp.ui.repository.GithubUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class UserListPresenter(
    private val view: UserListContract.View,
    private val repo: GithubUserRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
): CoroutinePresenter(dispatcher), UserListContract.Presenter {

    override fun getPagedData(): Flow<PagingData<UserListData>> {
        return repo.getPagingUserList()
    }

    override fun onStop() {
        job.cancel()
    }
}