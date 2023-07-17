package com.velocitypowered.api.kt.event.player

import com.velocitypowered.api.event.player.KickedFromServerEvent
import net.kyori.adventure.text.Component

inline val KickedFromServerEvent.RedirectPlayer.message: Component?
    get() = messageComponent

inline val KickedFromServerEvent.Notify.message: Component
    get() = messageComponent
