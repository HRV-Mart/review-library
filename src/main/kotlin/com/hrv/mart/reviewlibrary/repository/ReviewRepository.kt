package com.hrv.mart.reviewlibrary.repository

import com.hrv.mart.apicall.APICaller
import com.hrv.mart.custompageable.model.Pageable
import com.hrv.mart.custompageable.model.QueryParams
import com.hrv.mart.reviewlibrary.model.Review
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Repository
class ReviewRepository (
    @Value("\${hrv.mart.review.url}")
    private val reviewServerUrl: String
)
{
    private val webClientBuilder = WebClient.builder()
    private val apiCall = APICaller(webClientBuilder)

    fun createReview(review: Review, response: ServerHttpResponse) =
        apiCall
            .postRequest(
                reviewServerUrl,
                String::class.java,
                review,
                response
            )
    fun deleteReview(
        userId: String,
        productId: String,
        response: ServerHttpResponse
    ) =
        apiCall
            .deleteData(
                "$reviewServerUrl/$userId/$productId",
                String::class.java,
                response
            )
    fun getReviews(
        queryParams: QueryParams,
        size: Optional<Int>,
        page: Optional<Int>,
        response: ServerHttpResponse
    ) =
        apiCall
            .getData(
                "",
                Pageable::class.java,
                response
            )


}
