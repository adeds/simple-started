package id.adeds.started.data.repository

import id.adeds.started.data.local.SharedPrefs
import id.adeds.started.data.model.MyResponse
import id.adeds.started.data.model.User
import id.adeds.started.data.network.MyService
import id.adeds.started.util.asyncAwait

open class MyRepository(
    private val service: MyService,
    private val pref: SharedPrefs
) : MyDataSource {

    override suspend fun postDataFromNetwork(myParam: String, callback: ApiCallback<MyResponse>) {
        try {
            val response = asyncAwait {
                service.postSomething(myParam)
            }
            if (response.code.toString()[0] == '2') {
                callback.onSuccess(response)
            } else {
                callback.onFailed(Throwable(response.message))
            }

        } catch (e: Throwable) {
            callback.onFailed(Throwable(e.message))
        }
    }

    override suspend fun getDataFromNetwork(callback: ApiCallback<List<User>>) {
        try {
            val response = asyncAwait { service.getSomething() }
            callback.onSuccess(response)
        } catch (e: Throwable) {
            callback.onFailed(Throwable(e.message))
        }
    }
}