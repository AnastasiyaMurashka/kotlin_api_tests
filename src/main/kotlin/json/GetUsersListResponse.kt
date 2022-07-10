package json

import bo.Support
import bo.User

class GetUsersListResponse {
    private val page = 0
    private val per_page = 0
    private val total = 0
    private val total_pages = 0
    private val data: List<User>? = null
    private val support: Support? = null

    fun getPage(): Int {
        return page
    }

    fun getPer_page(): Int {
        return per_page
    }

    fun getTotal(): Int {
        return total
    }

    fun getTotalPages(): Int {
        return total_pages
    }

    fun getData(): List<User?>? {
        return data
    }

    fun getSupport(): Support? {
        return support
    }

    override fun toString(): String {
        return "GetUsersListResponse{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", totalPages=" + total_pages +
                ", data=" + data +
                ", support=" + support +
                '}'
    }
}