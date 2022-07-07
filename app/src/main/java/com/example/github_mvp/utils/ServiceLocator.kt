package com.example.github_mvp.utils

import com.example.github_mvp.SetupApplication
import com.example.github_mvp.ui.contract.UserDetailContract
import com.example.github_mvp.ui.contract.UserListContract
import com.example.github_mvp.ui.detail.UserDetailPresenter
import com.example.github_mvp.ui.list.UserListPresenter
import com.example.github_mvp.ui.repository.GithubUserRepository

object ServiceLocator {

    private fun getRepository(): GithubUserRepository {
        return SetupApplication.instance.getRepository()
    }

    fun getUserListPresenter(view: UserListContract.View): UserListContract.Presenter {
        return UserListPresenter(view, getRepository())
    }

    fun getUserDetailPresenter(view: UserDetailContract.View): UserDetailContract.Presenter {
        return UserDetailPresenter(view, getRepository())
    }

}