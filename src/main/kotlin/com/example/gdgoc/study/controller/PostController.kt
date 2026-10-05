package com.example.gdgoc.study.controller

import com.example.gdgoc.study.domain.Post
import com.example.gdgoc.study.dto.CreatePostRequest
import com.example.gdgoc.study.dto.PostResponse
import com.example.gdgoc.study.dto.UpdatePostRequest
import com.example.gdgoc.study.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @RequestBody request: CreatePostRequest,
    ): PostResponse = postService.createPost(title = request.title, content = request.content).toResponse()

    @GetMapping
    fun getPosts(): List<PostResponse> = postService.getPosts().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getPost(
        @PathVariable id: Int,
    ): PostResponse = postService.getPost(id).toResponse()

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable id: Int,
        @RequestBody request: UpdatePostRequest,
    ): PostResponse = postService.updatePost(id = id, title = request.title, content = request.content).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable id: Int,
    ) {
        postService.deletePost(id)
    }

    private fun Post.toResponse(): PostResponse = PostResponse(id = id, title = title, content = content)
}
