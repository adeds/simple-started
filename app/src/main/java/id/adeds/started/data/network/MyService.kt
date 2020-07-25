package id.adeds.started.data.network

import id.adeds.started.data.model.MyResponse
import id.adeds.started.data.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyService {

    @POST("endpoint")
    suspend fun postSomething(
        @Query("yourparam") param: String
    ): MyResponse

    @GET("users/{param}")
    suspend fun getSomething(
        @Path("param") param: String
    ): User
}