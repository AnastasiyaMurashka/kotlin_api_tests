package http

class HttpResponseBody<T>(private val statusCode: Int, private val body: T) {

    fun getResponseCode(): Int {
        return statusCode
    }

    fun getResponseBody(): T {
        return body
    }
}