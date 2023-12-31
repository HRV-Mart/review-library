package com.hrv.mart.reviewlibrary.model

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document

@Document("Review")
@CompoundIndex(
    name = "review_idx",
    def = "{'userId': 1, 'productId': 1}",
    unique = true
)
data class Review(
    val userId: String,
    val productId: String,
    val title: String,
    val description: String,
    val images: List<String>
)
