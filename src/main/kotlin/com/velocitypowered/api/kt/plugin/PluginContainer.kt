package com.velocitypowered.api.kt.plugin

import com.velocitypowered.api.plugin.PluginContainer

inline val PluginContainer.pInstance: Any
    get() = instance.orElse(null)