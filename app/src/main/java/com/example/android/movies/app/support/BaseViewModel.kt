package com.example.android.movies.app.support

import android.os.Bundle
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

    protected fun observer(
        dispatcher: CoroutineDispatcher = appDispatcher.main,
        observer: suspend () -> Unit
     ) = viewModelScope.launch(dispatcher) { observer.invoke() }

    open fun onSaveInstanceState() = Bundle()

    open fun onRestoreInstanceState(savedInstanceState: Bundle) {}

}