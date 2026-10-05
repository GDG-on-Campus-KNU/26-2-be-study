package com.example._0262_gdgoc_be_study.controller

import com.example._0262_gdgoc_be_study.domain.Post
import com.example._0262_gdgoc_be_study.dto.CreatePostRequest
import com.example._0262_gdgoc_be_study.dto.UpdatePostRequest
import com.example._0262_gdgoc_be_study.dto.PostResponse
import com.example._0262_gdgoc_be_study.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @RequestBody request: CreatePostRequest
    ): PostResponse {
        return postService.createPost(title = request.title, content = request.content).toResponse()
    }

    @GetMapping
    fun getPosts(): List<PostResponse> {
        return postService.getPosts().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getPost(
        @PathVariable id: Int
    ): PostResponse {
        return postService.getPost(id).toResponse()
    }

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable id: Int,
        @RequestBody request: UpdatePostRequest
    ): PostResponse {
        return postService.updatePost(id = id, title = request.title, content = request.content).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable id: Int
    ) {
        postService.deletePost(id)
    }

    private fun Post.toResponse(): PostResponse {
        return PostResponse(id = id, title = title, content = content)
    }
}
