package com.velocitypowered.api.kt.plugin

import com.velocitypowered.api.plugin.PluginContainer
import com.velocitypowered.api.plugin.PluginManager

fun PluginManager.plugin(pluginId: String): PluginContainer? =
    this.getPlugin(pluginId).orElse(null)