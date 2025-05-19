package com.example.trueorfalse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trueorfalse.ui.theme.TrueOrFalseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrueOrFalseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  Column(
                      modifier = Modifier.
                      fillMaxSize().
                      padding(innerPadding).
                      padding(16.dp),
                      verticalArrangement = Arrangement.SpaceBetween,
                      horizontalAlignment = Alignment.CenterHorizontally

                  ) {
                      Question()
                      ResultCircle(modifier = Modifier, isCorrect = true)
                      Row (modifier=Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                          CustomButton(buttonText = "False!", buttonType = false)
                          CustomButton(buttonText = "True!", buttonType = true)
                      }
                  }
                }
            }
        }
    }
}

@Composable
fun Question(modifier: Modifier=Modifier){
    Text(   text = "question?",
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold

    )
}

@Composable
fun ResultCircle(modifier: Modifier=Modifier,isCorrect: Boolean,){
    Box(
       modifier =  modifier.clip(CircleShape).height(300.dp).width(300.dp).background(Color.Green),


    )
}

@Composable
fun CustomButton(modifier: Modifier=Modifier,buttonText: String = "Button Text", buttonType: Boolean){
    Button(
        onClick = {},
        modifier = Modifier,
        colors = ButtonColors(
            contentColor =  Color.White ,
            containerColor = if (buttonType) Color.Blue else Color.Red,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp)

    ){
        Text(text = buttonText, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrueOrFalseTheme {
    }
}