package tw.andyang.kotlinandroidworkshop

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateTimeTest {
    @Test
    fun testDateTime() {
        val iso8601DatetimeString = "2020-09-21T04:34:56.789Z"
        // 上面這個 Z 這是 UTC+0 時區！不要把這個 Z 當一般文字處理！
        // 拜託不要再用 yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
        val date = SimpleDateFormat("y-M-d'T'H:m:s.SSSX").parse(iso8601DatetimeString)
        val localTimeString = SimpleDateFormat("yyyy-MM-dd HH:mm:ssX").apply {
            timeZone = TimeZone.getDefault()
        }.format(date)

        println(localTimeString)
    }
}