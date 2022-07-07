package com.example.github_mvp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.github_mvp.FragmentWithInfo
import com.example.github_mvp.data.UserDetailData
import com.example.github_mvp.databinding.FragmentUserDetailBinding
import com.example.github_mvp.ui.contract.UserDetailContract
import com.example.github_mvp.utils.ServiceLocator
import com.example.github_mvp.utils.getCircleCropImage
import com.example.github_mvp.utils.getUserDefaultImage

class UserDetailFragment private constructor(): FragmentWithInfo(), UserDetailContract.View {

    private val presenter by lazy {
        ServiceLocator.getUserDetailPresenter(this)
    }

    private lateinit var binding: FragmentUserDetailBinding
    companion object {
        private const val USER_ACCOUNT = "userName"
        fun newInstance(account: String): UserDetailFragment {
            return UserDetailFragment().apply {
                arguments = Bundle().apply {  putString(USER_ACCOUNT, account) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        arguments?.getString(USER_ACCOUNT)?.let { account ->
            presenter.fetchUserData(account)
        } ?: showError(NullPointerException(USER_ACCOUNT))
        binding.userDetailCancelButton.setOnClickListener{ onBackPressed() }
    }

    override fun startLoading() {
        binding.userDetailProgress.visibility = View.VISIBLE
    }

    override fun setUserDetail(detail: UserDetailData) {
        binding.userDetailName.text = detail.userName
        binding.userDetailBio.text = detail.userBio
        if (detail.imageWithHttps.isNullOrEmpty()) {
            binding.userDetailAvatar.getUserDefaultImage()
        } else {
            binding.userDetailAvatar.getCircleCropImage(detail.imageWithHttps!!)
        }
        binding.userDetailInfoLayout.apply {
            addView(UserDetailItemView(context).apply { setItemType(UserDetailItemType.Member, detail) })
            if (!detail.userLocation.isNullOrEmpty()) {
                addView(UserDetailItemView(context).apply { setItemType(UserDetailItemType.Location, detail) })
            }
            if (!detail.userBlog.isNullOrEmpty()) {
                addView(UserDetailItemView(context).apply { setItemType(UserDetailItemType.Blog, detail) })
            }
        }
    }

    override fun stopLoading() {
        binding.userDetailProgress.visibility = View.GONE
    }

    override fun showError(e: Throwable) {
        showErrorDialog(e.message ?: "Unknown Error")
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private fun onBackPressed() {
        activity?.onBackPressed()
    }
}