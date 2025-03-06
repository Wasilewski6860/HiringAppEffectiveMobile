@file:Suppress("unused")

package ru.hiringapp.base.result

import kotlinx.coroutines.flow.Flow

typealias AnyResult = OperationResult<Any>
typealias ListResult<T> = OperationResult<List<T>>
typealias MutableListResult<T> = OperationResult<MutableList<T>>
typealias FlowResult<T> = Flow<OperationResult<T>>
typealias FlowAnyResult = Flow<AnyResult>