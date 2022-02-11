package com.hadron.testrequestswithktorclient.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRsponse(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
)
