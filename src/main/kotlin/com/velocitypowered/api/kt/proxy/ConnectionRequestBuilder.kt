package com.velocitypowered.api.kt.proxy

import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import net.kyori.adventure.text.Component

inline val ConnectionRequestBuilder.Result.reason: Component?
    get() = this.reasonComponent.orElse(null)