package tw.andyang.kotlinandroidworkshop.network

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ApiClientTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testListUser() {
        runBlocking {
            launch(Dispatchers.Main) {
                val gson = Gson()
                val apiClient = ApiClient()
                println(gson.toJson(apiClient.listUser()))
            }
        }
    }

    @Test
    fun testGetUser() {
        runBlocking {
            launch(Dispatchers.Main) {
                val gson = Gson()
                val apiClient = ApiClient()
                println(gson.toJson(apiClient.getUser(1)))
            }
        }
    }

    @Test
    fun testCreateUser() {
        runBlocking {
            launch(Dispatchers.Main) {
                val gson = Gson()
                val apiClient = ApiClient()
                println(gson.toJson(apiClient.createUser("morpheus", "leader")))
            }
        }
    }
}