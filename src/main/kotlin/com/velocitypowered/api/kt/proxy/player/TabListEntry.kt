package com.velocitypowered.api.kt.proxy.player

import com.velocitypowered.api.proxy.player.TabListEntry
import net.kyori.adventure.text.Component


inline var TabListEntry.displayName: Component?
    get() = displayNameComponent.get()
    set(value) {
        setDisplayName(value)
    }


inline fun TabListEntry(builder: TabListEntry.Builder.() -> Unit): TabListEntry =
    TabListEntry.builder().apply(builder).build()
