package com.velocitypowered.api.kt.event

import com.velocitypowered.api.event.ResultedEvent
import net.kyori.adventure.text.Component

inline val ResultedEvent.ComponentResult.reason : Component?
    get() = this.reasonComponent.orElse(null)