package com.example._0262_gdgoc_be_study.service

import com.example._0262_gdgoc_be_study.domain.Post
import com.example._0262_gdgoc_be_study.dto.PostRequest
import com.example._0262_gdgoc_be_study.dto.PostResponse
import com.example._0262_gdgoc_be_study.repository.PostRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun createPost(request: PostRequest): PostResponse {
        val post = Post(
            id = postRepository.nextId(),
            title = request.title,
            content = request.content
        )
        postRepository.save(post)
        return post.toResponse()
    }

    fun getPosts(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    fun getPost(id: Int): PostResponse {
        return findPost(id).toResponse()
    }

    fun updatePost(id: Int, request: PostRequest): PostResponse {
        val existingPost = findPost(id)
        val post = Post(
            id = existingPost.id,
            title = request.title,
            content = request.content
        )
        postRepository.save(post)
        return post.toResponse()
    }

    fun deletePost(id: Int) {
        postRepository.delete(findPost(id))
    }

    private fun findPost(id: Int): Post {
        return postRepository.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Post $id not found")
    }

    /**
     * Post 객체를 PostResponse 객체로 변환하기 위한 확장 함수
     */
    private fun Post.toResponse(): PostResponse {
        return PostResponse(
            id = id,
            title = title,
            content = content
        )
    }
}
