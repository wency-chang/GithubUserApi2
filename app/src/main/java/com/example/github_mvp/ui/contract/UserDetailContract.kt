package com.example.github_mvp.ui.contract

import com.example.github_mvp.data.UserDetailData

interface UserDetailContract {
    interface View {
        fun startLoading()
        fun setUserDetail(detail: UserDetailData)
        fun stopLoading()
        fun showError(e: Throwable)
    }
    interface Presenter {
        fun fetchUserData(account: String)
        fun onStop()
    }
}