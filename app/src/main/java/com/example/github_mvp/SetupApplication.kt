package com.example.github_mvp

import android.app.Application
import com.example.github_mvp.service.GithubWebClientImp
import com.example.github_mvp.service.clientFactory.ClientFactory
import com.example.github_mvp.ui.repository.GithubUserRepository

class SetupApplication: Application() {

    companion object {
        private val repository: GithubUserRepository? = null
        val instance = this
        fun getRepository(): GithubUserRepository {
            return repository ?: (synchronized(this) {
                createRepository()
            })
        }
        private fun createRepository(): GithubUserRepository = GithubUserRepository(GithubWebClientImp)
    }

    override fun onCreate() {
        super.onCreate()
        setupService()
    }

    private fun setupService() {
        GithubWebClientImp.initService(
            ClientFactory.createGithubClient()
        )
    }
}