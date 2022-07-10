import http.HttpResponseBody
import json.GetSingleUserResponse
import json.GetUsersListResponse
import json.PostUserResponse
import json.PutPatchUserResponse
import okhttp3.Response
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApiUserTest : AbstractApiTest() {

    @Test
    fun checkGetListUsersResponse() {
        val response: HttpResponseBody<GetUsersListResponse>? =
            httpClient["users?page=2", GetUsersListResponse::class.java]
        Assertions.assertEquals(response?.getResponseCode(), 200)
        Assertions.assertEquals(response?.getResponseBody()?.getPage(), 2)
        Assertions.assertEquals(response?.getResponseBody()?.getData()?.size, 6)
    }

    @Test
    fun checkGetSingleUserResponse() {
        val response: HttpResponseBody<GetSingleUserResponse>? =
            httpClient["users/2", GetSingleUserResponse::class.java]
        Assertions.assertEquals(response?.getResponseCode(), 200)
        Assertions.assertEquals(response?.getResponseBody()?.getData()?.getId(), 2)
    }

    @Test
    fun checkGetClientNotFoundResponse() {
        val response: HttpResponseBody<GetSingleUserResponse>? =
            httpClient["users/23", GetSingleUserResponse::class.java]
        Assertions.assertEquals(response?.getResponseCode(), 404)
    }

    @Test
    fun checkPostUserResponse() {
        val requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}"
        val response: HttpResponseBody<PostUserResponse>? = httpClient.post(
            "unknown/23", requestBody,
            PostUserResponse::class.java
        )
        Assertions.assertEquals(response?.getResponseCode(), 201)
        Assertions.assertEquals(response?.getResponseBody()?.getName(), "morpheus")
        Assertions.assertEquals(response?.getResponseBody()?.getJob(), "leader")
    }

    @Test
    fun checkPutUserResponse() {
        val requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}"
        val response: HttpResponseBody<PutPatchUserResponse>? = httpClient.put(
            "unknown/23", requestBody,
            PutPatchUserResponse::class.java
        )
        Assertions.assertEquals(response?.getResponseCode(), 200)
        Assertions.assertEquals(response?.getResponseBody()?.getName(), "morpheus")
        Assertions.assertEquals(response?.getResponseBody()?.getJob(), "zion resident")
    }

    @Test
    fun checkPatchUserResponse() {
        val requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}"
        val response: HttpResponseBody<PutPatchUserResponse>? = httpClient.patch(
            "unknown/23", requestBody,
            PutPatchUserResponse::class.java
        )
        Assertions.assertEquals(response?.getResponseCode(), 200)
        Assertions.assertEquals(response?.getResponseBody()?.getName(), "morpheus")
        Assertions.assertEquals(response?.getResponseBody()?.getJob(), "zion resident")
    }

    @Test
    fun checkDeleteUserResponse() {
        val response: Response = httpClient.delete(
            "users/2"
        )
        Assertions.assertEquals(response.code(), 204)
    }
}
