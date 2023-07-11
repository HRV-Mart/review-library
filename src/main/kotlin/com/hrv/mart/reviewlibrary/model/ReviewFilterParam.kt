package com.hrv.mart.reviewlibrary.model

import org.springframework.web.bind.annotation.RequestParam
import java.util.*

data class ReviewFilterParam (
    val productId: Optional<String>,
    val userId: Optional<String>,
) {
    fun getQueryParamForURL(): String {
        var query = ""
        query = addParamInString(query, "productId", productId)
        query = addParamInString(query, "userId", userId)
        return query

    }
    private fun <T> addParamInString(currentQuery: String, paramName: String, param: Optional<T>) =
        if (param.isPresent)
            if (currentQuery.isEmpty())
                "?${paramName}=${param.get()}"
            else
                "${currentQuery}&${paramName}=${param.get()}"
        else
            currentQuery
}