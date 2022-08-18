package com.velocitypowered.api.kt.proxy

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.RegisteredServer
import java.util.UUID

fun ProxyServer.player(name: String): Player? = getPlayer(name).let { if (it.isPresent) it.get() else null }
fun ProxyServer.player(uuid: UUID): Player? = getPlayer(uuid).let { if (it.isPresent) it.get() else null }
fun ProxyServer.server(name: String): RegisteredServer? = getServer(name).let { if (it.isPresent) it.get() else null }
