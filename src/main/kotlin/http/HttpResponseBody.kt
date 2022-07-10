package http

class HttpResponseBody<T>(statusCode: Int, body: T) {
    private val statusCode = statusCode
    private val body = body

    fun getResponseCode(): Int {
        return statusCode
    }

    fun getResponseBody(): T {
        return body
    }
}