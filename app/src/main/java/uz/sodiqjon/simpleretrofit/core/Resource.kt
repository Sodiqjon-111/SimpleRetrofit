package uz.sodiqjon.simpleretrofit.core

sealed class  Resource<out T>(val status: Status) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    object Loading : Resource<Nothing>(Status.LOADING)
    data class Success<out T>(val data: T) : Resource<T>(Status.SUCCESS)
    data class Failure(val exception: Exception, val errorCode: Int = 400) :
        Resource<Nothing>(Status.ERROR)
}

sealed class ResourceFlow<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : ResourceFlow<T>(data)
    class Loading<T>(data: T? = null) : ResourceFlow<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : ResourceFlow<T>(data, throwable)
}