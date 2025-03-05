package ru.hiringapp.network.connection

import kotlinx.coroutines.flow.Flow

interface ConnectionProvider {

    val status: ConnectionStatus
    val hasInternet: Boolean
    fun observeConnection(): Flow<ConnectionStatus>
}