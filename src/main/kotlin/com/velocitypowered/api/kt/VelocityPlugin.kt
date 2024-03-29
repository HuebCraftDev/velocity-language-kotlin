package com.velocitypowered.api.kt

import com.google.inject.Inject
import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.kt.event.registerCoroutineContinuationAdapter
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger

@Plugin(
    id = "velocity-language-kotlin",
    authors = ["Velocity Contributors", "The HuebCraft Team"],
    version = BuildConstants.BUILD_VERSION
)
@Suppress("unused")
class VelocityPlugin @Inject constructor(
    val proxy: ProxyServer,
    private val logger: Logger
) {

    init {
        proxy.eventManager.registerCoroutineContinuationAdapter(logger)
    }

    @Subscribe(order = PostOrder.FIRST)
    fun onInit(event: ProxyInitializeEvent) {
        logger.info("The Kotlin Language Adapter is initialized!")
    }
}
