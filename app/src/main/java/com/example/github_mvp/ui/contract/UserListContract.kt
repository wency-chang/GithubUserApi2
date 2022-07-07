package com.example.github_mvp.ui.contract

import androidx.paging.PagingData
import com.example.github_mvp.data.UserListData
import kotlinx.coroutines.flow.Flow

interface UserListContract {
    interface View {
        fun startLoading()
        fun stopLoading()
        fun showError(msg: String?)
    }

    interface Presenter {
        fun getPagedData(): Flow<PagingData<UserListData>>
        fun onStop()
    }
}