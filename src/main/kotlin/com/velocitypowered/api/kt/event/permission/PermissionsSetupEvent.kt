package com.velocitypowered.api.kt.event.permission

import com.velocitypowered.api.event.permission.PermissionsSetupEvent


fun PermissionsSetupEvent.resetProvider() {
    provider = null
}
