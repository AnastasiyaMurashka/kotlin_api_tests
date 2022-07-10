package json

import bo.Support
import bo.User

class GetSingleUserResponse {

    private val data: User? = null
    private val support: Support? = null

    fun getData(): User? {
        return data
    }

    fun getSupport(): Support? {
        return support
    }
}