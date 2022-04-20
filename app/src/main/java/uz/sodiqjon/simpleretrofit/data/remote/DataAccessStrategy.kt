package uz.sodiqjon.simpleretrofit.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.sodiqjon.simpleretrofit.core.Resource
import uz.sodiqjon.simpleretrofit.core.ResourceFlow

fun <T> performGetOperation(
    networkCall: suspend () -> Resource<T>
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(ResourceFlow.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { ResourceFlow.Success(it) }
        } catch (throwable: Throwable) {
            query().map { ResourceFlow.Error(throwable, it) }
        }
    } else {
        query().map { ResourceFlow.Success(it) }
    }

    emitAll(flow)
}