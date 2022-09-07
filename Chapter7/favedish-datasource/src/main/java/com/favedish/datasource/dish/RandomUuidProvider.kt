package com.favedish.datasource.dish

import java.util.UUID

class RandomUuidProvider {
    fun randomUuid() = UUID.randomUUID().toString()
}
