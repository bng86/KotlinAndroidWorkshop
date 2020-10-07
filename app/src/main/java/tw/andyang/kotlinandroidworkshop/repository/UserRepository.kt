package tw.andyang.kotlinandroidworkshop.repository

import tw.andyang.kotlinandroidworkshop.network.ApiClient
import tw.andyang.kotlinandroidworkshop.network.entity.User

class UserRepository(private val apiClient: ApiClient) {

    suspend fun listUser(): List<User> {
        return apiClient.listUser()
    }

    suspend fun getUser(userId: Int): User {
        return apiClient.getUser(userId)
    }
}