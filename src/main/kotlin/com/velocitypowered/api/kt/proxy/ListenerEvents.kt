package com.velocitypowered.api.kt.proxy

import com.velocitypowered.api.event.proxy.ListenerBoundEvent
import com.velocitypowered.api.event.proxy.ListenerCloseEvent
import com.velocitypowered.api.network.ListenerType

inline val ListenerBoundEvent.type: ListenerType
    get() = listenerType

inline val ListenerCloseEvent.type: ListenerType
    get() = listenerType

