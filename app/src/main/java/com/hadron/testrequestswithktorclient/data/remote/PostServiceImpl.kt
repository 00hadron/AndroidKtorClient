package com.hadron.testrequestswithktorclient.data.remote

import com.hadron.testrequestswithktorclient.data.remote.dto.PostRequest
import com.hadron.testrequestswithktorclient.data.remote.dto.PostRsponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(
    private val client: HttpClient
) : PostService {
    override suspend fun getPosts(): List<PostRsponse> { // Resources class needed
       return try {
           client.get {
               url(HttpRoutes.POSTS)
           }
       } catch(e: RedirectResponseException) {
           // 3xx - responses
           println("Error: ${e.response.status.description}")
           emptyList()
       } catch(e: ClientRequestException) {
           // 4xx - responses
           println("Error: ${e.response.status.description}")
           emptyList()
       } catch(e: ServerResponseException) {
           // 5xx - responses
           println("Error: ${e.response.status.description}")
           emptyList()
       } catch(e: Exception) {
           println("Error: ${e.message}")
           emptyList()
       }
    }


    override suspend fun createPost(postRequest: PostRequest): PostRsponse? {
        return try {
            client.post<PostRsponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        }  catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}