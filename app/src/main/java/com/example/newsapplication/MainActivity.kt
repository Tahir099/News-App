package com.example.newsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.presentation.article_screen.ArticleScreen
import com.example.newsapplication.presentation.news_screen.NewsScreen
import com.example.newsapplication.presentation.news_screen.NewsScreenViewModel
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationTheme {
                Navigations()
            }
        }
    }
}

@Composable
fun Navigations(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "news_list_page"){
        composable("news_list_page"){
            val viewModel:NewsScreenViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
            )
        }
    }
}