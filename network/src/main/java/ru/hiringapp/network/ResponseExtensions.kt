package ru.hiringapp.network

import retrofit2.HttpException
import retrofit2.Response
import ru.hiringapp.base.result.OperationResult

fun <T> Response<T>.mapToOperationResult(): OperationResult<T> {
    return try {
        if (isSuccessful) {
            val body = body()
            if (body != null) {
                OperationResult.Success(body)
            } else {
                OperationResult.EmptyResult
            }
        } else {
            OperationResult.Error(HttpException(this))
        }
    } catch (e: Exception) {
        OperationResult.Error(e)
    }
}