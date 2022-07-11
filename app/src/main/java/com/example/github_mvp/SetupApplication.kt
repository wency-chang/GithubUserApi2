package com.example.github_mvp

import android.app.Application
import com.example.github_mvp.service.GithubWebClientImp
import com.example.github_mvp.service.clientFactory.ClientFactory
import com.example.github_mvp.ui.repository.GithubUserRepository

class SetupApplication: Application() {

    companion object {
        @Volatile
        private var repository: GithubUserRepository? = null
        val instance = this
        fun getRepository(): GithubUserRepository {
            return (synchronized(this) {
                repository ?: createRepository().also { newRepository ->
                    repository = newRepository
                }
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