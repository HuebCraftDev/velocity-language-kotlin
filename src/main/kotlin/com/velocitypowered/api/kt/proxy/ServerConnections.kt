package com.velocitypowered.api.kt.proxy

import com.velocitypowered.api.proxy.ServerConnection
import com.velocitypowered.api.proxy.server.RegisteredServer

inline val ServerConnection.previous: RegisteredServer?
    get() = this.previousServer.orElse(null)