package com.example.github_mvp.ui.detail

import com.example.github_mvp.ui.CoroutinePresenter
import com.example.github_mvp.ui.contract.UserDetailContract
import com.example.github_mvp.ui.repository.GithubUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UserDetailPresenter(
    private val view: UserDetailContract.View,
    private val repo: GithubUserRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
): CoroutinePresenter(dispatcher), UserDetailContract.Presenter {

    override fun fetchUserData(account: String) {
        coroutineScope.launchAndCatch(onError = {
            view.showError(it)
            view.stopLoading()
        }) {
            view.setUserDetail(repo.getUserDetail(account))
            view.stopLoading()
        }
    }

    override fun onStop() {
        job.cancel()
    }
}