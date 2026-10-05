package com.example._0262_gdgoc_be_study.controller

import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostHttpTests {
    @Value("\${local.server.port}")
    private var port: Int = 0

    private val client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()

    @Test
    fun `HTTP CRUD preserves success contract and maps missing posts to plain text 404`() {
        assertJson(request("GET", "/posts"), 200, "[]")

        val created = request("POST", "/posts", """{"title":"첫 제목","content":"첫 내용"}""")
        assertEquals(201, created.statusCode())
        val id = JSONObject(created.body()).getInt("id")
        val originalJson = """{"id":$id,"title":"첫 제목","content":"첫 내용"}"""
        assertJson(created, 201, originalJson)
        assertJson(request("GET", "/posts"), 200, "[$originalJson]")
        assertJson(request("GET", "/posts/$id"), 200, originalJson)

        val updatedJson = """{"id":$id,"title":"수정 제목","content":"수정 내용"}"""
        assertJson(
            request("PUT", "/posts/$id", """{"title":"수정 제목","content":"수정 내용"}"""),
            200,
            updatedJson
        )
        assertJson(request("GET", "/posts/$id"), 200, updatedJson)
        assertJson(request("GET", "/posts"), 200, "[$updatedJson]")

        val missingId = id + 1
        for (method in listOf("GET", "PUT", "DELETE")) {
            val body = if (method == "PUT") """{"title":"없는 제목","content":"없는 내용"}""" else null
            val response = request(method, "/posts/$missingId", body)
            assertEquals(404, response.statusCode(), method)
            assertEquals("Post $missingId not found", response.body(), method)
            assertJson(request("GET", "/posts"), 200, "[$updatedJson]")
        }

        val deleted = request("DELETE", "/posts/$id")
        assertEquals(204, deleted.statusCode())
        assertEquals("", deleted.body())
        assertJson(request("GET", "/posts"), 200, "[]")
        for (method in listOf("GET", "DELETE")) {
            val response = request(method, "/posts/$id")
            assertEquals(404, response.statusCode())
            assertEquals("Post $id not found", response.body())
        }
    }

    private fun request(method: String, path: String, body: String? = null): HttpResponse<String> {
        val publisher = body?.let { HttpRequest.BodyPublishers.ofString(it) }
            ?: HttpRequest.BodyPublishers.noBody()
        val request = HttpRequest.newBuilder(URI("http://localhost:$port$path"))
            .timeout(Duration.ofSeconds(10))
            .header("Content-Type", "application/json")
            .method(method, publisher)
            .build()
        return client.send(request, HttpResponse.BodyHandlers.ofString())
    }

    private fun assertJson(response: HttpResponse<String>, status: Int, expected: String) {
        assertEquals(status, response.statusCode())
        JSONAssert.assertEquals(expected, response.body(), true)
    }
}
