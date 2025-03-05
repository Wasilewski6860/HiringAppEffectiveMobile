package ru.hiringapp.network.connection.impl

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.hiringapp.network.connection.ConnectionProvider
import ru.hiringapp.network.connection.ConnectionStatus
import javax.inject.Inject

internal class ConnectionProviderImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ConnectionProvider {

    override val status: ConnectionStatus
        get() = connectivityManager.connectionStatus

    override val hasInternet: Boolean
        get() = connectivityManager.isConnected

    private val connectivityFlow by lazy {
        callbackFlow {
            val networkRequest = NetworkRequest.Builder().build()

            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(ConnectionStatus.CONNECTED)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(ConnectionStatus.NO_CONNECTION)
                }
            }

            connectivityManager.registerNetworkCallback(networkRequest, callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }
    }

    override fun observeConnection(): Flow<ConnectionStatus> {
        return connectivityFlow.debounce(300L).distinctUntilChanged()
    }
}