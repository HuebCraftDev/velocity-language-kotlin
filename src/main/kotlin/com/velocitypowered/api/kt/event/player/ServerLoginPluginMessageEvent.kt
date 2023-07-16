package com.velocitypowered.api.kt.event.player

import com.google.common.io.ByteArrayDataInput
import com.velocitypowered.api.event.player.ServerLoginPluginMessageEvent
import java.io.ByteArrayInputStream

inline val ServerLoginPluginMessageEvent.contentRaw: ByteArray
    get() = contents

inline val ServerLoginPluginMessageEvent.contentInputStream: ByteArrayInputStream
    get() = contentsAsInputStream()

inline val ServerLoginPluginMessageEvent.contentDataStream: ByteArrayDataInput
    get() = contentsAsDataStream()
