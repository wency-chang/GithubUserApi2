package com.example.github_mvp.data

sealed class ErrorExceptions(msg: String? = null): Exception() {
    class EmptyResultError(): ErrorExceptions()
    class ApiFailedError(val msg: String, val error: Exception? = null): ErrorExceptions(msg)
    class UnknownViewHolder(): ErrorExceptions()
}
