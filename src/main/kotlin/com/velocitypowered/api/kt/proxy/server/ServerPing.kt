package com.velocitypowered.api.kt.proxy.server

import com.velocitypowered.api.proxy.server.ServerPing
import com.velocitypowered.api.util.Favicon
import com.velocitypowered.api.util.ModInfo

fun ServerPing(builder: ServerPing.Builder.() -> Unit): ServerPing =
    ServerPing.builder().apply(builder).build()
inline val ServerPing.playerStats: ServerPing.Players?
    get() = players.orElse(null)
inline val ServerPing.favIcon: Favicon?
    get() = favicon.orElse(null)
inline val ServerPing.modInfo: ModInfo?
    get() = modinfo.orElse(null)
