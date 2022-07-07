package com.example.github_mvp.utils

import com.example.github_mvp.data.ErrorExceptions
import retrofit2.Response

fun <T> Response<T>.getBody(): T {
    return if (isSuccessful) {
        body() ?: run { throw ErrorExceptions.EmptyResultError() }
    } else {
        throw ErrorExceptions.ApiFailedError(message())
    }
}