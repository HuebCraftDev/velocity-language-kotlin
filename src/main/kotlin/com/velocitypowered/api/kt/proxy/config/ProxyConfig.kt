package com.velocitypowered.api.kt.proxy.config

import com.velocitypowered.api.proxy.config.ProxyConfig
import com.velocitypowered.api.util.Favicon

inline val ProxyConfig.favIcon: Favicon?
  get() = favicon.orElse(null)