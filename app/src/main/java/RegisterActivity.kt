package com.example.ymail2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterScreen()
        }
    }
}
@Composable
fun RegisterScreen(){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black).size(220.dp)){
        BackRegAccountButton(
            modifier = Modifier.align(Alignment.TopStart)
                .padding(top = 150.dp),
            onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        )
    }
}
@Composable
fun BackRegAccountButton(onClick: () -> Unit,
                       modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = "Регистрация.",
            fontFamily = FontFamily.Monospace,
            fontSize = 40.sp,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegScreenPreview() {
    RegisterScreen()
}