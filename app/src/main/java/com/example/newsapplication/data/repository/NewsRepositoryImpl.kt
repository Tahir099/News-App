package com.example.newsapplication.data.repository

import com.example.newsapplication.data.remote.NewsApi
import com.example.newsapplication.data.remote.dto.ArticleDto
import com.example.newsapplication.core.Resource
import com.example.newsapplication.domain.repository.NewsRepository
import retrofit2.HttpException
import java.io.IOException
class NewsRepositoryImpl(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(category: String): Resource<List<ArticleDto>> {
        return try {
            val news = api.getBreakNews(category)
            Resource.Success(news.articles)
        }
        catch (e:HttpException){
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }
        catch (e: IOException){
            Resource.Error("Couldn't reach server.Check your internet connection")
        }
    }

    override suspend fun searchForNews(query: String): Resource<List<ArticleDto>> {
        return try {
            val news = api.searchForNews(query)
            Resource.Success(news.articles)
        }
        catch (e:HttpException){
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }
        catch (e: IOException){
            Resource.Error("Couldn't reach server.Check your internet connection")
        }    }

}