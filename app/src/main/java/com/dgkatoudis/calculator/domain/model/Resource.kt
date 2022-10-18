package com.dgkatoudis.calculator.domain.model

sealed class Resource<T>(val data: T? = null, message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(data = null)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data = data, message = message)
}