package com.velocitypowered.api.kt.proxy

import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ServerConnection
import com.velocitypowered.api.proxy.server.RegisteredServer
import com.velocitypowered.api.util.ModInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

inline val Player.connectedServer: ServerConnection?
    get() = currentServer.orElse(null)

inline val Player.modInformation: ModInfo?
    get() = modInfo.orElse(null)

inline val Player.onlineMode: Boolean
    get() = isOnlineMode


suspend fun Player.connectTo(server: RegisteredServer): ConnectionRequestBuilder.Result =
    withContext(Dispatchers.IO) {
        createConnectionRequest(server).connect().join()
    }
