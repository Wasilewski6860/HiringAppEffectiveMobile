package ru.hiringapp.base.result

sealed class OperationResult<out R> {

    abstract class AbstractFailure : OperationResult<Nothing>() {
        abstract val error: Exception
    }

    data class Error(override val error: Exception) : AbstractFailure()

    open class Success<out T>(val data: T) : OperationResult<T>()

    data object EmptyResult : OperationResult<Nothing>()
}