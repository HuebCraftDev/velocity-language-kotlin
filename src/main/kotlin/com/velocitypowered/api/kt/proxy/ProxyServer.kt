package com.velocitypowered.api.kt.proxy

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.RegisteredServer
import java.util.UUID

fun ProxyServer.player(name: String): Player? = getPlayer(name).orElse(null)
fun ProxyServer.player(uuid: UUID): Player? = getPlayer(uuid).orElse(null)
fun ProxyServer.server(name: String): RegisteredServer? = getServer(name).orElse(null)
