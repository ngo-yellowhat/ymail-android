
package com.example.ymail2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.buildIntFloatMap
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SwipePages()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipePages() {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // PNG двигаются горизонтально при свайпе
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                when (page) {
                    0 -> Image(
                        painter = painterResource(R.drawable.orel),
                        contentDescription = "Орел",
                        modifier = Modifier
                            .size(230.dp)
                            .offset(y = 20.dp),
                        contentScale = ContentScale.Fit
                    )

                    1 -> Image(
                        painter = painterResource(R.drawable.whitehat),
                        contentDescription = "White hat",
                        modifier = Modifier
                            .width(270.dp)
                            .offset(y = 20.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        // Прозрачный слой: только текст и кнопки
        AnimatedContent(
            targetState = pagerState.currentPage,
            modifier = Modifier.fillMaxSize(),
            transitionSpec = {
                fadeIn(tween(450)) togetherWith fadeOut(tween(300))
            },
            label = "YmailContent"
        ) { page ->
            when (page) {
                0 -> YmailMainContent(
                    onContinue = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )

                1 -> YmailSecondContent()
            }
        }
    }
}

@Composable
fun YmailMainContent(onContinue: () -> Unit) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Добро пожаловать в\nymail.",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 120.dp),
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp,
            lineHeight = 36.sp
        )

        Text(
            text = "Свобода слова и приватность\n" +
                    "невозможны без кода,\n" +
                    "который нельзя тихо изменить или отобрать.",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 230.dp),
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            lineHeight = 17.sp
        )

        YmailButton(
            text = "Продолжить",
            onClick = onContinue,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)
        )
    }
}

@Composable
fun YmailSecondContent() {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Создайте аккаунт.",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 120.dp),
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            fontSize = 30.sp,
            lineHeight = 36.sp
        )

        Text(
            text = "Открытый код принадлежит всем — он развивается\n" +
                    "вместе, а не диктуется сверху.",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 230.dp),
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            lineHeight = 17.sp
        )

        YmailButton(
            text = "Создать",
            onClick = {
                context.startActivity(
                    Intent(context, RegisterActivity::class.java)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)
        )

        TextButton(
            onClick = {
                context.startActivity(
                    Intent(context, EnterActivity::class.java)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        ) {
            Text(
                text = "Войти в аккаунт",
                color = Color.Gray,
            )
        }
    }
}

@Composable
fun YmailButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(width = 300.dp, height = 44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFD100),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace
        )
    }
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
            contentDescription = "Орел",
            modifier = Modifier
                .size(230.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit
        )
    }
}
