package com.example.ymail2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun main(onBack: () -> Unit) {
    TODO("Not yet implemented")
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun YmailPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        YmailMainContent(onContinue = {})
        Image(
            painter = painterResource(R.drawable.orel),
            contentDescription = null,
            modifier = Modifier
                .size(230.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit
        )
    }
}
