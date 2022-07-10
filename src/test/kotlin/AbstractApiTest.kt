import http.HttpClient

abstract class AbstractApiTest {
    protected val httpClient = HttpClient()
}