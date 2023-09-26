package com.example.newsapplication.domain.repository

import com.example.newsapplication.data.remote.dto.ArticleDto
import com.example.newsapplication.core.Resource

interface NewsRepository {
    suspend fun getTopHeadlines(category: String): Resource<List<ArticleDto>>

    suspend fun searchForNews(
        query: String
    ):Resource<List<ArticleDto>>
}