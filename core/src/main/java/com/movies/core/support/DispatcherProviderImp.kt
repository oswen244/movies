package com.movies.core.support

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImp: DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val network: CoroutineDispatcher
        get() = Dispatchers.IO
}