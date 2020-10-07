package tw.andyang.kotlinandroidworkshop.network

import retrofit2.Response
import retrofit2.http.*
import tw.andyang.kotlinandroidworkshop.network.entity.Envelope
import tw.andyang.kotlinandroidworkshop.network.entity.User

interface UserApi {

    companion object {
        const val PATH_ARG_USER_ID = "user_id"
    }

    // 注意！不要斜線開頭！
    @GET("users")
    suspend fun listUser(): Response<Envelope<List<User>>>

    @GET("users/{$PATH_ARG_USER_ID}")
    suspend fun getUser(@Path(PATH_ARG_USER_ID) userId: Int): Response<Envelope<User>>

    @FormUrlEncoded
    @POST("users")
    suspend fun createUser(@Field("name") userName: String, @Field("job") job: String): Response<User>
}