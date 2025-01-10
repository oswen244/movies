package com.example.android.movies.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.core.support.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val appDispatcher: DispatcherProvider): ViewModel() {

    protected fun execute(
        dispatcher: CoroutineDispatcher = appDispatcher.main,
        action: suspend () -> Unit
    ) = viewModelScope.launch(dispatcher) { action() }
}