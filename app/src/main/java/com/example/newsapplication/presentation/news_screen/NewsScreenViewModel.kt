package com.example.newsapplication.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.remote.dto.ArticleDto
import com.example.newsapplication.core.Resource
import com.example.newsapplication.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject


@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(){
    var state by mutableStateOf(NewsScreenState())
    fun onEvent(event:NewsScreenEvent){
        when(event){
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(state.category)
            }
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchForNews(query = state.searchQuery)
            }

            else -> {}
        }
    }

    init {
        getNewsArticles(category = "Health")
    }
    private fun getNewsArticles(category: String){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = newsRepository.getTopHeadlines(category = category)){
                is Resource.Success ->{
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error ->{
                    state = state.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                else -> {}
            }
        }
    }

    private fun searchForNews(query: String){
        if(query.isBlank()){
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = newsRepository.searchForNews(query = query)){
                is Resource.Success ->{
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error ->{
                    state = state.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                else -> {}
            }
        }
    }
}