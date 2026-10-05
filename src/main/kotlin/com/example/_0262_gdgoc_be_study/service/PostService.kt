package com.example._0262_gdgoc_be_study.service

import com.example._0262_gdgoc_be_study.domain.Post
import com.example._0262_gdgoc_be_study.domain.PostNotFoundException
import com.example._0262_gdgoc_be_study.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun createPost(title: String, content: String): Post {
        val post = Post(
            id = postRepository.nextId(),
            title = title,
            content = content
        )
        postRepository.save(post)
        return post
    }

    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun getPost(id: Int): Post {
        return findPost(id)
    }

    fun updatePost(id: Int, title: String, content: String): Post {
        val existingPost = findPost(id)
        val post = Post(
            id = existingPost.id,
            title = title,
            content = content
        )
        postRepository.save(post)
        return post
    }

    fun deletePost(id: Int) {
        postRepository.delete(findPost(id))
    }

    private fun findPost(id: Int): Post {
        return postRepository.findById(id)
            ?: throw PostNotFoundException(id)
    }
}
