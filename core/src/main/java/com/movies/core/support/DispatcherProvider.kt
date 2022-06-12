package com.movies.core.support

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val network: CoroutineDispatcher
}