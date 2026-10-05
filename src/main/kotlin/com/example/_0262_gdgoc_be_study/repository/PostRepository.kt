package com.example._0262_gdgoc_be_study.repository

import com.example._0262_gdgoc_be_study.domain.Post
import org.springframework.stereotype.Repository

interface PostRepository {
    fun nextId(): Int
    fun findById(id: Int): Post?
    fun findAll(): ArrayList<Post>
    fun save(post: Post)
    fun delete(post: Post)
}

/**
 * In-Memory Repository Implementation
 */
@Repository
class PostRepositoryImpl(
    private val posts: ArrayList<Post> = arrayListOf()
): PostRepository {
    private var nextPostId = (posts.maxOfOrNull { it.id } ?: 0) + 1

    override fun nextId(): Int {
        return nextPostId++
    }

    override fun findById(id: Int): Post? {
        return posts.find { it.id == id }
    }

    override fun findAll(): ArrayList<Post> {
        return ArrayList(posts)
    }

    override fun save(post: Post) {
        val index = posts.indexOfFirst { it.id == post.id }
        if (index == -1) {
            posts.add(post)
        } else {
            posts[index] = post
        }
    }

    override fun delete(post: Post) {
        posts.removeIf { it.id == post.id }
    }
}
