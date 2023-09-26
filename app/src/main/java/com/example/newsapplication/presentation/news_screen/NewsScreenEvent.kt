package com.example.newsapplication.presentation.news_screen

import com.example.newsapplication.data.remote.dto.ArticleDto

sealed class NewsScreenEvent{
    data class OnNewsCardClicked(val article:ArticleDto):NewsScreenEvent()
    data class OnCategoryChanged(val category:String):NewsScreenEvent()
    data class OnSearchQueryChanged(val searchQuery:String):NewsScreenEvent()
}
