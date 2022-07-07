package com.example.github_mvp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.github_mvp.R

fun Fragment.navigateTo(fragment: Fragment) {
    parentFragmentManager.commit {
        replace(R.id.fragment_container, fragment)
        addToBackStack(null)
    }
}

fun AppCompatActivity.removeCurrentFragment() {
    supportFragmentManager.findFragmentById(R.id.fragment_container)?.let {
        supportFragmentManager.beginTransaction()
            .remove(it)
            .commitNow()
    }
}

private fun FragmentManager.commit(execute: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        execute()
        commit()
    }
}