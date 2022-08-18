package com.velocitypowered.api.kt.event.player

import com.velocitypowered.api.event.player.ServerPreConnectEvent
import com.velocitypowered.api.proxy.server.RegisteredServer

inline val ServerPreConnectEvent.ServerResult.target: RegisteredServer?
  get() = server.let { if (it.isPresent) it.get() else null }
