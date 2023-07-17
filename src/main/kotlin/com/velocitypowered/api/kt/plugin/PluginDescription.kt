package com.velocitypowered.api.kt.plugin

import com.velocitypowered.api.plugin.PluginDescription
import com.velocitypowered.api.plugin.meta.PluginDependency
import java.nio.file.Path

inline val PluginDescription.pName: String?
    get() = this.name.orElse(null)
inline val PluginDescription.pVersion: String?
    get() = this.version.orElse(null)
inline val PluginDescription.pAuthors: List<String>
    get() = this.authors
inline val PluginDescription.pDescription: String?
    get() = this.description.orElse(null)
inline val PluginDescription.pUrl: String?
    get() = this.url.orElse(null)
inline val PluginDescription.src: Path?
    get() = this.source.orElse(null)
fun PluginDescription.dependency(id: String): PluginDependency? =
    this.getDependency(id).orElse(null)
