package com.example.gdgoc.study.service

import com.example.gdgoc.study.domain.PostNotFoundException
import com.example.gdgoc.study.repository.PostRepositoryImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertSame

class PostServiceTests {
    private val repository = PostRepositoryImpl()
    private val service = PostService(repository)

    @Test
    fun `CRUD preserves post fields and update identity`() {
        assertEquals(emptyList(), service.getPosts())

        val first = service.createPost("첫 제목", "첫 내용")
        val second = service.createPost("두 번째 제목", "두 번째 내용")

        assertNotEquals(first.id, second.id)
        assertEquals("첫 제목", first.title)
        assertEquals("첫 내용", first.content)
        assertSame(first, service.getPost(first.id))
        assertEquals(listOf(first, second), service.getPosts())

        val updated = service.updatePost(first.id, "수정 제목", "수정 내용")

        assertEquals(first.id, updated.id)
        assertEquals("수정 제목", updated.title)
        assertEquals("수정 내용", updated.content)
        assertSame(updated, service.getPost(first.id))
        assertEquals(listOf(updated, second), service.getPosts())

        service.deletePost(first.id)

        assertEquals(listOf(second), service.getPosts())
        assertFailsWith<PostNotFoundException> { service.getPost(first.id) }
        assertFailsWith<PostNotFoundException> { service.deletePost(first.id) }
        assertNotEquals(first.id, service.createPost("새 제목", "새 내용").id)
    }

    @ParameterizedTest
    @ValueSource(strings = ["get", "update", "delete"])
    fun `missing post throws domain exception without changing stored posts`(operation: String) {
        val existing = service.createPost("제목", "내용")
        val missingId = existing.id + 1

        val exception = assertFailsWith<PostNotFoundException> {
            when (operation) {
                "get" -> service.getPost(missingId)
                "update" -> service.updatePost(missingId, "변경 제목", "변경 내용")
                "delete" -> service.deletePost(missingId)
            }
        }

        assertEquals("Post $missingId not found", exception.message)
        assertEquals(listOf(existing), service.getPosts())
        assertSame(existing, service.getPost(existing.id))
    }
}
