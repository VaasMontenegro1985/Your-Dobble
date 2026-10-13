package com.vasya.yourdobble.ui.screen

import androidx.compose.foundation.background
import androidx.compose.ui.tooling.preview.Preview

/*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.vasya.yourdobble.ui.theme.YourDobbleTheme

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    YourDobbleTheme {
        StartScreen(
            onStartClick = {}
        )
    }
}

@Composable
fun StartScreen(onStartClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(text = "Your Dobble")
            Button(onClick = onStartClick){
                Text("Начать игру")
            }
            Button(onClick = onStartClick){
                Text("Нас")
            }
        }
    }
}
 */


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vasya.yourdobble.R
import com.vasya.yourdobble.ui.theme.YourDobbleTheme

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun StartScreenPreview() {
    YourDobbleTheme {
        StartScreen(
            onSoloClick = {},
            onLocalMultiClick = {},
            onOnlineMultiClick = {},
            onHowToPlayClick = {},
            onSettingsClick = {}
        )
    }
}

@Composable
fun StartScreen(
    onSoloClick: () -> Unit,
    onLocalMultiClick: () -> Unit,
    onOnlineMultiClick: () -> Unit,
    onHowToPlayClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val backgroundAlpha by animateFloatAsState(0.85f, tween(800))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF3E8FF),
                        Color(0xFFE0F2FE),
                        Color(0xFFFFF7ED)
                    )
                )
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.6f)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 48.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.fire_it_up_logo),
                    contentDescription = "Логотип Fire It Up",
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(180.dp),
                    contentScale = ContentScale.Fit
                )

            }

            // Центральные кнопки
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                GameButton(
                    text = "Одиночная игра",
                    onClick = onSoloClick,
                    isPrimary = true
                )

                GameButton(
                    text = "Игра вдвоём на одном устройстве",
                    onClick = onLocalMultiClick,
                    isPrimary = false
                )

                GameButton(
                    text = "Онлайн мультиплеер",
                    onClick = onOnlineMultiClick,
                    isPrimary = false,
                    enabled = false // пока в планах
                )
            }

            // Нижняя панель
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = onHowToPlayClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_help),
                        contentDescription = "Как играть"
                    )
                }
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "Настройки"
                    )
                }
            }
        }
    }
}

@Composable
fun GameButton(
    text: String,
    onClick: () -> Unit,
    isPrimary: Boolean,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp),
        shape = MaterialTheme.shapes.large,
        colors = if (isPrimary) {
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center
        )
    }
}