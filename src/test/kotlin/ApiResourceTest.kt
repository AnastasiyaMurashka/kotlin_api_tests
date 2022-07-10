import http.HttpResponseBody
import json.GetListResourceResponse
import json.GetSingleResourceResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApiResourceTest : AbstractApiTest() {

    @Test
    fun checkGetListResourceResponse() {
        val response: HttpResponseBody<GetListResourceResponse>? =
            httpClient["unknown", GetListResourceResponse::class.java]
        Assertions.assertEquals(response?.getResponseCode(), 200)
        Assertions.assertEquals(response?.getResponseBody()?.getData()?.size, 6)
    }

    @Test
    fun checkGetSingleResourceResponse() {
        val response: HttpResponseBody<GetSingleResourceResponse>? =
            httpClient["unknown/2", GetSingleResourceResponse::class.java]
        Assertions.assertEquals(response?.getResponseCode(), 200)
        Assertions.assertEquals(response?.getResponseBody()?.getData()?.getId(), 2)
    }

    @Test
    fun checkGetResourceNotFoundResponse() {
        val response: HttpResponseBody<GetSingleResourceResponse>? =
            httpClient["unknown/23", GetSingleResourceResponse::class.java]
        Assertions.assertEquals(response?.getResponseCode(), 404)
    }
}
