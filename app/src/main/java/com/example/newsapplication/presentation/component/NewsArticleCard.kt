package com.example.newsapplication.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.data.remote.dto.ArticleDto
import com.example.newsapplication.R
import com.example.newsapplication.util.dateFormatter

@Composable
fun NewsArticleCard(
    modifier:Modifier = Modifier,
    article: ArticleDto,
    onCardClicked:(ArticleDto) -> Unit
) {
    val date = dateFormatter(article.publishedAt)
    Card(
        modifier = modifier.clickable {
            onCardClicked(article)
        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            ImageHolder(imageUrl = article.urlToImage , Modifier.fillMaxWidth(0.3f))
            Spacer(modifier = Modifier.width(8.dp))
            Column{
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp)) {
                    Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = "" , Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$date • ${article.source.name ?: "unknown"}" ,
                        style = TextStyle(fontSize = 15.sp),maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
