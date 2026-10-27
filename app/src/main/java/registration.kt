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
fun RegistrationScreen(onBack: () -> Unit, onHaveAccount: () -> Unit,  onContinue: () -> Unit,) {




    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        Text(
            text = "ВНИМАНИЕ \n" + "после нажатия кнопки на экране появится секретная фраза \n" + "обеспечьте безопасность фразы",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 120.dp)
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
fun SecretPhraseScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = "Здесь появится секретная фраза",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

    @Composable
    fun LoginScreen(onBack: () -> Unit) {
        TODO("Not yet implemented")
    }

