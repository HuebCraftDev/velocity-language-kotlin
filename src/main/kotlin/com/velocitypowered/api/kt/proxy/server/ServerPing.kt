package com.velocitypowered.api.kt.proxy.server

import com.velocitypowered.api.proxy.server.ServerPing

fun ServerPing(builder: ServerPing.Builder.() -> Unit): ServerPing =
    ServerPing.builder().apply(builder).build()
