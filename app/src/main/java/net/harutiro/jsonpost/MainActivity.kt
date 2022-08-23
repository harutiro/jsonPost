package net.harutiro.jsonpost

import android.icu.util.TimeUnit
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.harutiro.jsonpost.ui.theme.JsonPostTheme
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JsonPostTheme {

                var json by rememberSaveable { mutableStateOf("") }
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column() {
                        Button(
                            onClick = {startPostRequest()}
                        ){
                            Text("Post")
                        }

                        Button(
                            onClick = {startGetRequest()}
                        ){
                            Text("Get")
                        }
                    }
                }
            }
        }
    }

    val CONNECTION_TIMEOUT_MILLISECONDS = 1000
    val READ_TIMEOUT_MILLISECONDS = 1000

    private val client = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT_MILLISECONDS.toLong(), java.util.concurrent.TimeUnit.MILLISECONDS
        )
        .readTimeout(READ_TIMEOUT_MILLISECONDS.toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
        .build()


    fun startGetRequest() {
        // Requestを作成
        val request = Request.Builder()
            .url("https://xclothes.harutiro.net/login?mail=harutiro2727@gmail.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Responseの読み出し
                val responseBody = response.body?.string().orEmpty()
                val code = response.code
                Log.d("get", "body: $responseBody code: $code")


//                val responseBody = "[" +
//                        "{" +
//                        "    \"id\": \"hfC7PvTtMp\"," +
//                        "    \"coordinate_id\": \"h4C7PvTtp\"," +
//                        "    \"user_id\": \"fnJSu2HaM\"," +
//                        "    \"put_flag\": 1," +
//                        "    \"public\": 1," +
//                        "    \"image\": \"https://test/test.jpg\"," +
//                        "    \"category\": \"jacket\"," +
//                        "    \"brand\": \"globalworks\"," +
//                        "    \"price\": \"5000-7000円\"," +
//                        "    \"ble\": \"f3b635c8-5b2a-47a1-9360-c3ffbbd6c8fe\"," +
//                        "    \"created_at\": \"2022-08-16T05:52:25+09:00\"," +
//                        "    \"update_at\": \"2022-08-16T15:13:30+09:00\"" +
//                        "}," +
//                        "{" +
//                        "    \"id\": \"J4k7gXHap\"," +
//                        "    \"coordinate_id\": \"h4C7PvTtp\"," +
//                        "    \"user_id\": \"fnJSu2HaM\"," +
//                        "    \"put_flag\": 1," +
//                        "    \"public\": 1," +
//                        "    \"image\": \"https://test/test.jpg\"," +
//                        "    \"category\": \"socks\"," +
//                        "    \"brand\": \"uniclo\"," +
//                        "    \"price\": \"0-1000円\"," +
//                        "    \"ble\": \"f3b635c8-5b2a-47a1-9360-c3ffbbd6c8fe\"," +
//                        "    \"created_at\": \"2022-08-16T05:52:25+09:00\"," +
//                        "    \"update_at\": \"2022-08-16T15:13:30+09:00\"" +
//                        "}," +
//                        "{" +
//                        "    \"id\": \"JU2_EXHtpp\"," +
//                        "    \"coordinate_id\": \"GI2VEXHtM\"," +
//                        "    \"user_id\": \"fnJSu2HaM\"," +
//                        "    \"put_flag\": 2," +
//                        "    \"public\": 1," +
//                        "    \"image\": \"https://test/test.jpg\"," +
//                        "    \"category\": \"jacket\"," +
//                        "    \"brand\": \"globalworks\"," +
//                        "    \"price\": \"5000-7000円\"," +
//                        "    \"ble\": \"6fc7ec4f-a389-415c-b791-767d9c376afc\"," +
//                        "    \"created_at\": \"2022-08-16T06:13:30+09:00\"," +
//                        "    \"update_at\": \"2022-08-16T06:13:30+09:00\"" +
//                        "}," +
//                        "{" +
//                        "    \"id\": \"kIlVEXTaM\"," +
//                        "    \"coordinate_id\": \"GI2VEXHtM\"," +
//                        "    \"user_id\": \"fnJSu2HaM\"," +
//                        "    \"put_flag\": 2," +
//                        "    \"public\": 1," +
//                        "    \"image\": \"https://test/test.jpg\"," +
//                        "    \"category\": \"socks\"," +
//                        "    \"brand\": \"uniclo\"," +
//                        "    \"price\": \"0-1000円\"," +
//                        "    \"ble\": \"6fc7ec4f-a389-415c-b791-767d9c376afc\"," +
//                        "    \"created_at\": \"2022-08-16T06:13:30+09:00\"," +
//                        "    \"update_at\": \"2022-08-16T06:13:30+09:00\"" +
//                        "}," +
//                        "" +
//                        "]"


//                val listType = object : TypeToken<List<UserData>>() {}.type
//                val hogeData = Gson().fromJson<List<UserData>>(responseBody, listType)
//
//                Log.d("App", hogeData.toString())

                hogeData.toString()


                // 必要に応じてCallback
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallback
            }
        })
    }

    fun startPostRequest() {
        // Bodyのデータ（サンプル）
        val JSON_MEDIA = "application/json; charset=utf-8".toMediaType()

        val sendDataJson = "{" +
                "\t\"name\": \"yada\"," +
                "\t\"uuid\":\"e9c8c6b8-9927-c15a-4675-dcc9fae3d119\"," +
                "\t\"icon\": \"https://test.net/yada.jpg\"," +
                "\t\"gender\": 1," +
                "\t\"age\": \"20〜25\"," +
                "\t\"height\": 175," +
                "\t\"mail\": \"yada@aitech.ac.jp\"" +
                "}"

        val urlStr = "http://20.168.98.13:8080/logi"

        // Requestを作成
        val request = Request.Builder()
            .url(urlStr)
            .post(sendDataJson.toRequestBody(JSON_MEDIA))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Responseの読み出し
                val responseBody = response.body?.string().orEmpty()
                val code = response.code
                Log.d("get", "body: $responseBody code: $code")
                // 必要に応じてCallback
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallback
            }
        })
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JsonPostTheme {
        Greeting("Android")
    }
}