package com.velocitypowered.api.kt.event.player

import com.velocitypowered.api.event.player.PlayerChatEvent

fun PlayerChatEvent.messageResult(message: String) = PlayerChatEvent.ChatResult.message(message)!!
