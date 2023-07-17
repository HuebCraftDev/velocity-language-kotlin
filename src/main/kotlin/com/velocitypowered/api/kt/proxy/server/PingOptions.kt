package com.velocitypowered.api.kt.proxy.server

import com.velocitypowered.api.proxy.server.PingOptions

fun PingOptions(builder: PingOptions.Builder.() -> Unit): PingOptions =
    PingOptions.builder().apply(builder).build()