package com.example.newsapplication.presentation.news_screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.R
import com.example.newsapplication.data.remote.dto.ArticleDto
import com.example.newsapplication.presentation.component.NewsArticleCard
import com.example.newsapplication.presentation.component.RetryContent

@Composable
fun NewsScreen(
    state: NewsScreenState,
    onEvent: (NewsScreenEvent) -> Unit
) {
    val categories = listOf(
        "General" , "Business" , "Health" , "Science" , "Sports" , "Technology" , "Entertainment"
    )
    var selectedIndex by remember { mutableStateOf(0) }
    val query = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = selectedIndex){
        onEvent(NewsScreenEvent.OnCategoryChanged(category = categories[selectedIndex]))
    }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query.value,
            onValueChange ={
                query.value = it
                onEvent(NewsScreenEvent.OnSearchQueryChanged(query.value))
            }
            ,

            modifier = Modifier

                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    1.dp, Color.Gray, RoundedCornerShape(15.dp)
                ),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
            }
        )
        LazyRow(
            Modifier
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
        ){
            itemsIndexed(categories){index ,category ->
                Text(
                    text = category,
                    modifier = Modifier.clickable {
                        selectedIndex = index
                    },
                    style = TextStyle(
                        color = if(index == selectedIndex) Color.Blue else Color.Gray,
                        fontSize = 18.sp
                    ),
                )
            }
        }
     /**/   NewsArticleList(
            state = state,
            onCardClicked = {article ->
                onEvent(NewsScreenEvent.OnNewsCardClicked(article = article))
            },
            onRetry = {
                onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
            }
        )

    }
}

@Composable
fun NewsArticleList(
    state: NewsScreenState,
    onCardClicked:(ArticleDto) -> Unit,
    onRetry:() -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(12.dp)
    ){
        items(state.articles){article ->
            NewsArticleCard(article = article, onCardClicked = onCardClicked)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(state.isLoading){
            CircularProgressIndicator()
        }
        if(state.error != null){
            RetryContent(
                error = state.error,
                onRetry = onRetry,
            )
        }
    }
}