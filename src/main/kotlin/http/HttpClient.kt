package http

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.util.*

class HttpClient {
    private val baseUrl = "https://reqres.in/api/"
    private val applicationJson = "application/json; charset=utf-8"
    private val client = OkHttpClient()
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    operator fun <T> get(urlPart: String, clazz: Class<T>): HttpResponseBody<T> {
        val url = String.format("%s%s", baseUrl, urlPart)
        val request = Request.Builder().url(url).build()
        val call = client.newCall(request)
        return try {
            val response = call.execute()
            getResponseAsObject(response, clazz)
        } catch (e: IOException) {
            throw UnsupportedOperationException("Exception during sending get request")
        }
    }

    fun <T> post(urlPart: String, jsonBody: String, clazz: Class<T>): HttpResponseBody<T> {
        val url = String.format("%s%s", baseUrl, urlPart)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse(applicationJson), jsonBody)
        val request = Request.Builder().url(url).post(requestBody).build()
        val call = client.newCall(request)
        return try {
            val response = call.execute()
            getResponseAsObject(response, clazz)
        } catch (e: IOException) {
            throw UnsupportedOperationException("Exception during sending post request")
        }
    }

    fun <T> put(urlPart: String, jsonBody: String, clazz: Class<T>): HttpResponseBody<T> {
        val url = String.format("%s%s", baseUrl, urlPart)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse(applicationJson), jsonBody)
        val request = Request.Builder().url(url).put(requestBody).build()
        val call = client.newCall(request)
        return try {
            val response = call.execute()
            getResponseAsObject(response, clazz)
        } catch (e: IOException) {
            throw UnsupportedOperationException("Exception during sending put request")
        }
    }

    fun <T> patch(urlPart: String, jsonBody: String, clazz: Class<T>): HttpResponseBody<T> {
        val url = String.format("%s%s", baseUrl, urlPart)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse(applicationJson), jsonBody)
        val request = Request.Builder().url(url).patch(requestBody).build()
        val call = client.newCall(request)
        return try {
            val response = call.execute()
            getResponseAsObject(response, clazz)
        } catch (e: IOException) {
            throw UnsupportedOperationException("Exception during sending patch request")
        }
    }

    fun delete(urlPart: String): Response {
        val url = String.format("%s%s", baseUrl, urlPart)
        val request = Request.Builder().url(url).delete().build()
        val call = client.newCall(request)
        return try {
            call.execute()
        } catch (e: IOException) {
            throw UnsupportedOperationException("Exception during sending delete request")
        }
    }

    @Throws(IOException::class)
    private fun <T> getResponseAsObject(response: Response, clazz: Class<T>): HttpResponseBody<T> {
        val statusCode = response.code()
        val responseBody = Objects.requireNonNull(response.body()).string()
        val body: T = gson.fromJson(responseBody, clazz)
        return HttpResponseBody(statusCode, body)
    }
}