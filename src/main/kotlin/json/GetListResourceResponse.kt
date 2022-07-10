package json

import bo.Resource
import bo.Support

class GetListResourceResponse {
    private val page = 0
    private val per_page = 0
    private val total = 0
    private val total_pages = 0
    private val data: List<Resource?>? = null
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

    fun getData(): List<Resource?>? {
        return data
    }

    fun getSupport(): Support? {
        return support
    }
}