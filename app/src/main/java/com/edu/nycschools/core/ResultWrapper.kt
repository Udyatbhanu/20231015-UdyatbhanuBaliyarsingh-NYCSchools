package com.edu.nycschools.core


data class ErrorResponse(
    val errorDescription: String, // this is the translated error shown to the user directly from the API
    val causes: Map<String, String> = emptyMap() //this is for errors on specific field on a form
)

/**
 * Wrapper class to handle api responses.
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()

    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    data object NetworkError : ResultWrapper<Nothing>()

    data object EmptyResponse : ResultWrapper<Nothing>()
}