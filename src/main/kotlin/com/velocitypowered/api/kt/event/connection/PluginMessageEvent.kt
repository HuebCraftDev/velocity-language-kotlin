package com.velocitypowered.api.kt.event.connection

import com.google.common.io.ByteArrayDataInput
import com.velocitypowered.api.event.connection.PluginMessageEvent
import java.io.ByteArrayInputStream


inline val PluginMessageEvent.messageRaw: ByteArray
  get() = data

inline val PluginMessageEvent.messageInputStream: ByteArrayInputStream
  get() = dataAsInputStream()

inline val PluginMessageEvent.messageDataStream: ByteArrayDataInput
  get() = dataAsDataStream()
