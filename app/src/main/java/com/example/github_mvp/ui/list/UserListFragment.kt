package com.example.github_mvp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.github_mvp.FragmentWithInfo
import com.example.github_mvp.databinding.FragmentUserListBinding
import com.example.github_mvp.ui.contract.UserListContract
import com.example.github_mvp.ui.detail.UserDetailFragment
import com.example.github_mvp.utils.ServiceLocator
import com.example.github_mvp.utils.navigateTo

class UserListFragment: FragmentWithInfo(), UserListContract.View {
    private lateinit var binding: FragmentUserListBinding
    private val presenter by lazy {
        ServiceLocator.getUserListPresenter(this)
    }
    private val adapter by lazy { UserListAdapter { account ->
        navigateTo(UserDetailFragment.newInstance(account))
    } }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        binding.userListRecycler.adapter = this.adapter
        adapter.addLoadStateListener {
            when(it.refresh) {
                is LoadState.Loading -> startLoading()
                is LoadState.NotLoading -> stopLoading()
                is LoadState.Error -> showError((it.refresh as? LoadState.Error)?.error?.message)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        lifecycleScope.launchWhenStarted {
            presenter.getPagedData().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun startLoading() {
        binding.userListProgress.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        binding.userListProgress.visibility = View.GONE
    }

    override fun showError(msg: String?) {
        showErrorDialog(msg ?: "Unknown Error")
        stopLoading()
    }
}
