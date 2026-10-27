package com.example.ymail2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

enum class Screen { Onboarding, Registration, Login, SecretPhrase  }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    var currentScreen by remember { mutableStateOf(Screen.Onboarding) }

    when (currentScreen) {
        Screen.Onboarding -> SwipePages(
            onRegister = { currentScreen = Screen.Registration },
            onLogin = { currentScreen = Screen.Login }
        )
        Screen.Registration -> RegistrationScreen(
            onBack = { currentScreen = Screen.Onboarding },
            onHaveAccount = { currentScreen = Screen.Login },
            onContinue = { currentScreen = Screen.SecretPhrase },
        )
        Screen.Login -> LoginScreen(
            onBack = { currentScreen = Screen.Onboarding }
        )
        Screen.SecretPhrase -> SecretPhraseScreen(
            onBack = {
                currentScreen = Screen.Registration
            }
        )
    }
}





@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipePages(
    onRegister: () -> Unit,
    onLogin: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
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
                        painter = painterResource(R.drawable.ymail),
                        contentDescription = null,
                        modifier = Modifier.size(270.dp),
                        contentScale = ContentScale.Fit
                    )
                    1 -> Image(
                        painter = painterResource(R.drawable.orel),
                        contentDescription = null,
                        modifier = Modifier
                            .width(270.dp)
                            .size(270.dp),
                        contentScale = ContentScale.Fit
                    )
                    2 -> Image(
                        painter = painterResource(R.drawable.whitehat),
                        contentDescription = null,
                        modifier = Modifier
                            .size(270.dp)
                            .width(270.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

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
                    onContinue = { scope.launch { pagerState.animateScrollToPage(1) } }
                )
                1 -> YmailMiddleContent(
                    onContinue = { scope.launch { pagerState.animateScrollToPage(2) } }
                )
                2 -> YmailtridContent(
                    onRegister = onRegister,
                    onLogin = onLogin
                )
            }
        }
    }
}

@Composable
fun YmailMainContent(onContinue: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Добро пожаловать!",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 120.dp)
        )

        Text(
            text =
                    "приватная почта для всех, полностью бесплатно.\n" +
                    "без искуственных ограничений.",
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
fun YmailMiddleContent(onContinue: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Никто не вправе забрать свободу слова.",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 120.dp)
        )

        Text(
            text = "государство не вправе запретить вам говорить.\n" +
                    "не вправе за вами следить.",
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            lineHeight = 17.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 230.dp)
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
fun YmailtridContent(
    onRegister: () -> Unit,
    onLogin: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Присоединяйтесь к нам!",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 120.dp)
        )

        Text(
            text = "строим наше будущее вместе! \n" +
                    "каждый пользователь может внести свой вклад.",
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
            onClick = onRegister,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)
        )

        TextButton(
            onClick = onLogin,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        ) {
            Text(
                text = "Войти в аккаунт",
                color = Color.Gray
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
