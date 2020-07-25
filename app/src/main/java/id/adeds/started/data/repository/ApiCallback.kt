package id.adeds.started.data.repository


interface ApiCallback<T> {
    fun onSuccess(data: T)
    fun onFailed(e: Throwable)
}