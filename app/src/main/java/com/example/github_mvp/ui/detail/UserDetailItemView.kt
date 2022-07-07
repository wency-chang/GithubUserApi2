package com.example.github_mvp.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.example.github_mvp.R
import com.example.github_mvp.data.HTTP
import com.example.github_mvp.data.HTTPS
import com.example.github_mvp.data.UserDetailData
import com.example.github_mvp.databinding.ItemUserDetailBinding


class UserDetailItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ItemUserDetailBinding.inflate(LayoutInflater.from(context), this)

    fun setItemType(type: UserDetailItemType, data: UserDetailData) {
        layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setPadding(20)
        when (type) {
            UserDetailItemType.Member -> setupMember(data)
            UserDetailItemType.Location -> data.userLocation?.let { setupLocation(it) }
            UserDetailItemType.Blog -> data.userBlog?.let { setupBlog(it) }
        }
    }

    private fun setupMember(data: UserDetailData) {
        binding.userDetailItemIcon.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_person_24, resources.newTheme())
        )
        binding.userDetailItemMainTitle.text = data.userAccount
        if (data.isStaff) {
            binding.userDetailBadge.visibility = VISIBLE
        }
    }

    private fun setupLocation(location: String) {
        binding.userDetailItemIcon.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_location_on_24, resources.newTheme())
        )
        binding.userDetailItemMainTitle.text = location
    }
    private fun setupBlog(blog: String) {
        binding.userDetailItemIcon.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_link_24, resources.newTheme())
        )
        binding.userDetailItemMainTitle.apply {
            setTextColor(context.getColor(R.color.blue_link))
            val content = SpannableString(blog)
            content.setSpan(UnderlineSpan(), 0, blog.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = content
            setOnClickListener {
                (context as? Activity)?.startActivity(
                    Intent(Intent.ACTION_VIEW, getUri(blog))
                )
            }
        }
    }

    private fun getUri(link: String): Uri {
        return if (link.startsWith(HTTPS)) {
            Uri.parse(link)
        } else if (link.startsWith(HTTP)) {
            Uri.parse(link.replace(HTTP, HTTPS, ignoreCase = true))
        } else  Uri.parse(HTTPS + link)
    }
}