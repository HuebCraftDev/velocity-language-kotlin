package com.velocitypowered.api.kt.command

import com.velocitypowered.api.command.CommandMeta

inline fun CommandMeta.Builder.build(crossinline buildFun: CommandMeta.Builder.() -> Unit): CommandMeta {
    buildFun()
    return build()
}
