package com.example.github_mvp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.github_mvp.R
import com.example.github_mvp.data.HTTP
import com.example.github_mvp.data.HTTPS
import com.example.github_mvp.data.UserListData
import com.example.github_mvp.databinding.ViewholderUserListBinding
import com.example.github_mvp.utils.getCircleCropImage
import com.example.github_mvp.utils.getUserDefaultImage

class UserListAdapter(private val onItemClick: (String) -> Unit): PagingDataAdapter<UserListData, ViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(ViewHolderType.from(viewType)) {
            ViewHolderType.User -> UserListViewHolder(
                ViewholderUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ViewHolderType.Header -> object : ViewHolder(getHeaderView(parent.context)) { }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is UserListViewHolder -> {
                getItem(position)?.let { data ->
                    holder.bindData(data)
                    holder.itemView.setOnClickListener {
                        data.userAccount?.let { onItemClick(it) }
                    }
                }
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.type?.id ?: 0
    }

    inner class UserListViewHolder(private val binding: ViewholderUserListBinding): ViewHolder(binding.root) {
        fun bindData(data: UserListData) {
            binding.userListName.text = data.userAccount ?: ""
            if (data.isStaff) {
                binding.userBadge.visibility = View.VISIBLE
            } else {
                binding.userBadge.visibility = View.GONE
            }
            if (data.userImage.isNullOrEmpty()) {
                binding.usrListAvatar.getUserDefaultImage()
            } else {
                val url = if (data.userImage.startsWith(HTTPS)) {
                    data.userImage
                } else if (data.userImage.startsWith(HTTP)) {
                    data.userImage.replace(HTTP, HTTPS, ignoreCase = true)
                } else HTTPS + data.userImage
                binding.usrListAvatar.getCircleCropImage(url)
            }
        }
    }

    companion object DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<UserListData>() {
        override fun areItemsTheSame(oldItem: UserListData, newItem: UserListData): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: UserListData, newItem: UserListData): Boolean {
            return oldItem == newItem
        }
        private const val HEADER_VIEW_TEXT = "Users"
    }
    private fun getHeaderView(context: Context): TextView = TextView(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        setPadding(5)
        text = HEADER_VIEW_TEXT
        setTextColor(context.getColor(R.color.grey))
        textSize = 20f
    }
}