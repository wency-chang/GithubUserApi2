package com.example.github_mvp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.github_mvp.databinding.ActivityMainBinding
import com.example.github_mvp.ui.list.UserListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportActionBar?.title = LIST_FRAGMENT_TOOLBAR
        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container, UserListFragment()
        ).addToBackStack("userList").commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}
private const val LIST_FRAGMENT_TOOLBAR = "Github Users"