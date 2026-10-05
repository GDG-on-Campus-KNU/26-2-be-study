package com.example._0262_gdgoc_be_study.domain

class PostNotFoundException(id: Int) : RuntimeException("Post $id not found")
