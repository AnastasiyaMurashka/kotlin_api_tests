package json

import bo.Resource
import bo.Support

class GetSingleResourceResponse {

    private lateinit var data: Resource
    private lateinit var support: Support

    fun getData(): Resource {
        return data
    }

    fun getSupport(): Support {
        return support
    }
}