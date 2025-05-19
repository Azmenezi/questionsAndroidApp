package com.example.trueorfalse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trueorfalse.ui.theme.TrueOrFalseTheme

val questions = listOf(
    mapOf("question" to "The Earth revolves around the Sun.", "answer" to true),
    mapOf("question" to "Bats are blind.",                    "answer" to false),
    mapOf("question" to "Java was released before Kotlin.",   "answer" to true)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TrueOrFalseTheme {
                var answered by rememberSaveable { mutableStateOf(false) }
                var chosenCorrect by rememberSaveable { mutableStateOf(false) }
                var qIndex by rememberSaveable { mutableStateOf(0) }
                var currentQ by rememberSaveable { mutableStateOf<Map<String, Any>>(questions[0]) }
                var score by rememberSaveable { mutableStateOf(0) }

                Scaffold(Modifier.fillMaxSize()) { inner ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(inner)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment  = Alignment.CenterHorizontally
                    ) {
                        Question(text = currentQ["question"] as String)

                        if (answered) ResultCircle(isCorrect = chosenCorrect,score=score)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            if(!answered){
                            CustomButton(
                                text           = "False!",
                                buttonType     = false,
                                answered       = answered,
                                onAnswer       = { isTrue ->
                                    answered      = true
                                    chosenCorrect = (isTrue == currentQ["answer"])
                                    if(chosenCorrect)score++
                                },
                                onNext = {
                                    qIndex   = (qIndex + 1) % questions.size
                                    answered = false
                                    currentQ = questions[qIndex]
                                }
                            )

                                CustomButton(
                                    text           = "True!",
                                    buttonType     = true,
                                    answered       = answered,
                                    onAnswer       = { isTrue ->
                                        answered      = true
                                        chosenCorrect = (isTrue == currentQ["answer"])
                                        if(chosenCorrect)score++
                                    },
                                    onNext = {

                                    }
                                )
                            }else{
                                CustomButton(
                                    text           = "Next!",
                                    buttonType     = true,
                                    answered       = answered,
                                    onAnswer       = { isTrue ->
                                        answered      = true
                                        chosenCorrect = (isTrue == currentQ["answer"])
                                    },
                                    onNext = {
                                        qIndex   = (qIndex + 1) % questions.size
                                        answered = false
                                        currentQ = questions[qIndex]
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Question(text: String) =
    Text(text, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)

@Composable
fun ResultCircle(isCorrect: Boolean,score:Int) {
    val color = if (isCorrect) Color.Green else Color.Red
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(300.dp)
            .background(color),
    ){
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = score.toString(), fontSize = 50.sp)
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    buttonType: Boolean,
    answered: Boolean,
    onAnswer: (Boolean) -> Unit,
    onNext: () -> Unit
) {
    Button(
        onClick = {
            if (answered) onNext() else onAnswer(buttonType)
            Log.d("Click", "answered=$answered buttonType=$buttonType")
        },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor         = if (buttonType) Color.Blue else Color.Red,
            contentColor           = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor   = Color.White
        )
    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() = TrueOrFalseTheme { Question("Preview") }
