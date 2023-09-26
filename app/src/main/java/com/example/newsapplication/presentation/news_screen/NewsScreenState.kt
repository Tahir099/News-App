package com.example.newsapplication.presentation.news_screen

import com.example.newsapplication.data.remote.dto.ArticleDto

data class NewsScreenState(
    val isLoading:Boolean = false,
    val articles:List<ArticleDto> = emptyList(),
    val error:String? = null,
    val isSearchBarVisible:Boolean = false,
    val selectedArticle:ArticleDto? = null,
    val category:String = "General",
    val searchQuery:String = ""
)