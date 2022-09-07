package com.favedish.domain.test.coroutine

import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers

object FakeCoroutineContextProvider : CoroutineContextProvider {
    override val main = Dispatchers.Unconfined
    override val io = Dispatchers.Unconfined
}
