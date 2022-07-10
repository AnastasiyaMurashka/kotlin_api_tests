import bo.User
import http.HttpClient
import http.HttpResponseBody
import json.GetUsersListResponse
import json.PostRegisterResponse
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import utils.RandomStringGenerator.Companion.getRandCharSequence
import java.util.*

@TestMethodOrder(OrderAnnotation::class)
class ApiRegisterTest : AbstractApiTest() {
    private val errorMessage = "Missing password"

    companion object {
        var user: User
        var password: String
        init {
            val rand = Random()
            val responseUsers: HttpResponseBody<GetUsersListResponse> =
                HttpClient()["users?page=2", GetUsersListResponse::class.java]
            val users: List<User> = responseUsers.getResponseBody().getData() as List<User>
            user = users[rand.nextInt(users.size)]
            password = getRandCharSequence(7)
        }
    }

    @Test
    @Order(1)
    fun checkPostSuccessfulRegisterResponse() {
        val requestBody = "{\"email\": \"" + user.getEmail() + "\", \"password\": \"" + password + "\"}"
        val response: HttpResponseBody<PostRegisterResponse> = httpClient.post(
            "register", requestBody,
            PostRegisterResponse::class.java
        )
        Assertions.assertEquals(
            response.getResponseCode(), 200
        )
        response.getResponseBody().getToken()?.let { Assertions.assertFalse(it.isEmpty()) }
    }

    @Test
    @Order(2)
    fun checkPostSuccessfulLoginResponse() {
        val requestBody = "{\"email\": \"" + user.getEmail() + "\", \"password\": \"" + password + "\"}"
        val response: HttpResponseBody<PostRegisterResponse> = httpClient.post(
            "login", requestBody,
            PostRegisterResponse::class.java
        )
        Assertions.assertEquals(
            response.getResponseCode(), 200
        )
        response.getResponseBody().getToken()?.let { Assertions.assertFalse(it.isEmpty(), "Token is empty") }
    }

    @Test
    fun checkPostUnsuccessfulRegisterResponse() {
        val requestBody = "{\"email\": \"sydney@fife\"}"
        val response: HttpResponseBody<PostErrorResponse> = httpClient.post(
            "register", requestBody,
            PostErrorResponse::class.java
        )
        Assertions.assertEquals(response.getResponseCode(), 400)
        Assertions.assertEquals(response.getResponseBody().getError(), errorMessage)
    }

    @Test
    fun checkPostUnsuccessfulLoginResponse() {
        val requestBody = "{\"email\": \"sydney@fife\"}"
        val response: HttpResponseBody<PostErrorResponse> = httpClient.post(
            "login", requestBody,
            PostErrorResponse::class.java
        )
        Assertions.assertEquals(response.getResponseCode(), 400)
        Assertions.assertEquals(response.getResponseBody().getError(), errorMessage)
    }
}