package com.example.gdgoc.study.domain

class PostNotFoundException(id: Int) : RuntimeException("Post $id not found")
