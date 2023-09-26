package com.example.newsapplication.data.remote

import com.example.newsapplication.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getBreakNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey:String = API_KEY
    ): NewsResponseDto

    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query:String,
        @Query("apiKey") apiKey:String = API_KEY
    ):NewsResponseDto

    companion object{
        const val API_KEY = "8bccb7d0e0e04eacac5764a41b63bf6d"
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}