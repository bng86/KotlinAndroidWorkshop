package tw.andyang.kotlinandroidworkshop.network

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.andyang.kotlinandroidworkshop.network.entity.User

class ApiClient {

    private val okHttp: Call.Factory
    private val retrofit: Retrofit
    private val userApi: UserApi


    constructor(baseUrl: String = "https://reqres.in/api/") {
        this.okHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY); })
            .build()
        this.retrofit = Retrofit.Builder()
            // 注意！base url 要斜線結尾！
            .baseUrl(baseUrl)
            .client(this.okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.userApi = retrofit.create(UserApi::class.java)
    }

    suspend fun listUser(): List<User> {
        try {
            val response = userApi.listUser()
            return if (response.isSuccessful) {
                response.body()!!.data
            } else {
                throw Exception("should success")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getUser(userId: Int): User {
        try {
            val response = userApi.getUser(userId)
            return if (response.isSuccessful) {
                response.body()!!.data
            } else {
                throw Exception("should success")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun createUser(userName: String, job: String): User {
        try {
            val response = userApi.createUser(userName, job)
            return if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception("should success")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}