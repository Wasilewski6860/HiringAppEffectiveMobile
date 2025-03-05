package ru.hiringapp.network.connection.impl

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import ru.hiringapp.network.connection.ConnectionStatus

@Suppress("DEPRECATION")
internal val ConnectivityManager.connectionStatus: ConnectionStatus
    @SuppressLint("ObsoleteSdkInt")
    get() {
        val hasConnection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = this.getNetworkCapabilities(this.activeNetwork)
            capabilities?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN)
            } ?: false
        } else {
            var isWifiConn = false
            var isMobileConn = false
            this.allNetworks.forEach { network ->
                this.getNetworkInfo(network)?.apply {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isWifiConn = isWifiConn or isConnected
                    }
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                        isMobileConn = isMobileConn or isConnected
                    }
                }
            }
            isWifiConn || isMobileConn
        }
        return if(hasConnection) {
            ConnectionStatus.CONNECTED
        } else {
            ConnectionStatus.NO_CONNECTION
        }
    }

internal val ConnectivityManager.isConnected: Boolean get() = this.connectionStatus == ConnectionStatus.CONNECTED