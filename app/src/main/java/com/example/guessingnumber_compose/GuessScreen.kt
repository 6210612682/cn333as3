package com.example.guessingnumber_compose

import androidx.compose.material.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random


@Composable
fun GuessScreen(navController: NavController) {
    val finalGuess = remember { mutableStateOf("")}
    val count = remember { mutableStateOf(0)}
    val helperText = remember { mutableStateOf("Give a number")}
    val randomNumber = remember { mutableStateOf(5) }

    LaunchedEffect(key1 = true) {
        randomNumber.value = Random.nextInt(1001) // 0-1000
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guessing Counting: ${count.value}",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "Help: ${helperText.value}",
            fontSize = 24.sp,
            color = Color.Black
        )
        TextField(
            value = finalGuess.value,
            onValueChange = {
                finalGuess.value = it
            },
            label = {
                Text(text = "Guess")
            }
        )

        Button(
            onClick = {
                val predictedNumber = finalGuess.value.toInt()

                if (predictedNumber == randomNumber.value) {
                    navController.navigate("last_screen") {
                        popUpTo("guess_screen") {
                            inclusive = true
                        }
                    }
                    return@Button

                }
                if (predictedNumber > randomNumber.value) {
                    helperText.value = "Decrease"
                }
                if (predictedNumber < randomNumber.value) {
                    helperText.value = "Increase"
                }
                count.value = count.value + 1
                finalGuess.value = ""
            },
            modifier = Modifier.size(150.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        )
        {
            Text(
                text = "Guess",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}