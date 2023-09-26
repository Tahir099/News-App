package com.example.newsapplication.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun RetryContent(
    error: String,
    onRetry:()->Unit,
    modifier:Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = error , color = Color.Red , fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick =  onRetry , Modifier.align(CenterHorizontally)) {
            Text(text = "Retry")
        }
    }
}