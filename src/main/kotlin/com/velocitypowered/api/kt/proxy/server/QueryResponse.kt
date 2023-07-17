package com.velocitypowered.api.kt.proxy.server

import com.velocitypowered.api.proxy.server.QueryResponse

inline fun QueryResponse(builder: QueryResponse.Builder.() -> Unit): QueryResponse =
    QueryResponse.builder().apply(builder).build()
inline val QueryResponse.builder: QueryResponse.Builder
    get() = this.toBuilder()