package ru.hiringapp.base.result.extension

import ru.hiringapp.base.result.OperationResult

inline fun <I> OperationResult<I>.onSuccess(
    executable: (I) -> Unit,
): OperationResult<I> {
    if (this is OperationResult.Success) executable(data)
    return this
}

inline fun <I> OperationResult<I>.onCompletion(
    executable: () -> Unit,
): OperationResult<I> {
    executable()
    return this
}

inline fun <I> OperationResult<I>.onEmpty(
    executable: () -> Unit,
): OperationResult<I> {
    if (this is OperationResult.EmptyResult) executable()
    return this
}

inline fun <I> OperationResult<I>.onError(
    executable: (Exception) -> Unit,
): OperationResult<I> {
    if (this is OperationResult.AbstractFailure) {
        executable(error)
    }
    return this
}