package id.adeds.started.data.repository

import id.adeds.started.data.model.MyResponse
import id.adeds.started.data.model.User

interface MyDataSource {
    suspend fun postDataFromNetwork(myParam: String, callback: ApiCallback<MyResponse>)
    suspend fun getDataFromNetwork(myParam: String, callback: ApiCallback<User>)
}