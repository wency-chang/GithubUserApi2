package com.example.github_mvp.service.clientFactory

import com.example.github_mvp.service.GithubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientFactory {
    private const val GITHUB_CLIENT_DOMAIN = "https://api.github.com/"
    private const val TIMEOUT_SECONDS = 30L
    @JvmStatic
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @JvmStatic
    fun createGithubClient(): GithubService {
        return Retrofit.Builder()
            .baseUrl(GITHUB_CLIENT_DOMAIN)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }
}