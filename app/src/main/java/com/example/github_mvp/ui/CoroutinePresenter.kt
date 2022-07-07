package com.example.github_mvp.ui

import kotlinx.coroutines.*

abstract class CoroutinePresenter(dispatcher: CoroutineDispatcher) {

    protected val job by lazy { SupervisorJob() }
    protected val coroutineScope by lazy { CoroutineScope(dispatcher + job) }

    protected inline fun CoroutineScope.launchAndCatch(
        crossinline onError: (Throwable) -> Unit = { },
        crossinline execute: suspend CoroutineScope.() -> Unit): Job {
        return this.launch(CoroutineExceptionHandler { _, error ->
            onError.invoke(error)
        }) {
            execute()
        }
    }
}