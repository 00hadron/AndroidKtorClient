package com.hadron.testrequestswithktorclient.data.remote

import com.hadron.testrequestswithktorclient.data.remote.dto.PostRequest
import com.hadron.testrequestswithktorclient.data.remote.dto.PostRsponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostService {
    suspend fun getPosts(): List<PostRsponse>

    suspend fun createPost(postRequest: PostRequest): PostRsponse?

    companion object {
        fun create(): PostService {
            return PostServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}