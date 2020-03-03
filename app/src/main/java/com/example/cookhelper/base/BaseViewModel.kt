package com.example.cookhelper.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookhelper.entities.AsyncResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


abstract class BaseViewModel : ViewModel(), KoinComponent {
    var statusMutableLiveData = MutableLiveData<Status>()
    val messageLiveData = MutableLiveData<String>()
    private val coroutineJob = Job()
    protected val scopeProvider: CoroutineProvider by inject()
    protected val scope = CoroutineScope(coroutineJob + scopeProvider.IO)

    override fun onCleared() {
        super.onCleared()
        coroutineJob.cancel()
    }

    /**
     * Presentation-layer-обработчик для запросов через `kotlin coroutines`:
     * запускает [Job] в [scope],
     * вызывает прогресс на [statusMutableLiveData]
     *
     * [call] - `suspend`-функция запроса из репозитория
     * [resultBlock] - функция, которую нужно выполнить по завершении запроса в UI-потоке
     */
    fun <T> makeRequest(
        call: suspend CoroutineScope.() -> T,
        resultBlock: ((T) -> Unit)? = null
    ) = scope.launch(scopeProvider.Main) {
        set(Status.SHOW_LOADING)
        val result = withContext(scopeProvider.IO, call)
        resultBlock?.invoke(result)
        set(Status.HIDE_LOADING)
    }

    /**
     * Обработчик для ответов [AsyncResult] репозитория.
     * [errorBlock] - функция обработки ошибок, можно передать `null`, чтобы никак не обрабатывать.
     * [successBlock] - обработка непустого результата
     */
    protected fun <T> unwrap(
        result: AsyncResult<T>,
        errorBlock: ((String) -> Unit)? = { setError(it) },
        successBlock: (T) -> Unit
    ) = when (result) {
        is AsyncResult.Success -> result.result?.let { successBlock(it) }
        is AsyncResult.Error -> errorBlock?.invoke(result.error)
    }

    fun set(status: Status) {
        statusMutableLiveData.postValue(status)
    }

    fun setError(error: String) {
        messageLiveData.postValue(error)
    }

    fun io(work: suspend (() -> Unit)): Job =
        scope.launch(scopeProvider.IO) {
            work()
        }

    fun <T : Any> ioThenMain(work: suspend (() -> T?), callback: ((T?) -> Unit)): Job =
        scope.launch(scopeProvider.Main) {
            val data = scope.async(scopeProvider.IO) rt@{
                return@rt work()
            }.await()
            callback(data)
        }
}