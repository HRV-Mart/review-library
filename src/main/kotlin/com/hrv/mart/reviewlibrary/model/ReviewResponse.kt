package com.hrv.mart.reviewlibrary.model

import com.hrv.mart.userlibrary.model.User

data class ReviewResponse(
    val review: Review,
    val user: User
)
