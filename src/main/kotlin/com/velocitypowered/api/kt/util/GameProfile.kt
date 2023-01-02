package com.velocitypowered.api.kt.util

import com.velocitypowered.api.util.GameProfile

operator fun GameProfile.plus(properties: Iterable<GameProfile.Property>): GameProfile =
    addProperties(properties)

operator fun GameProfile.plus(property: GameProfile.Property): GameProfile =
    addProperty(property)
