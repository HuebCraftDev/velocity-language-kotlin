package com.velocitypowered.api.kt.event.player

import com.velocitypowered.api.event.player.ServerConnectedEvent
import com.velocitypowered.api.proxy.server.RegisteredServer

inline val ServerConnectedEvent.target: RegisteredServer
    get() = server
