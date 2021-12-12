package com.myfitness.fitnesscompose

import android.content.Context
import android.os.Bundle
import android.view.Surface
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myfitness.fitnesscompose.ui.theme.FitnessComposeTheme
import dagger.internal.DaggerCollections
import dagger.Component
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var coachThis: Coach
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            FitnessComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Trainer: "+coachThis.defaultCoach)

                }
            }
        }
    }
}

@Composable
fun ButtonComponent(buttonText: String) {
    var haveFocus by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var color by remember { mutableStateOf(Black) }
    val focusRequester = FocusRequester()

    Text(
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { color = if (it.isFocused) Green else Black }
            .focusModifier()
            .pointerInput(Unit) { detectTapGestures { focusRequester.requestFocus() } },
        text = "Text",
        color = color
    )
    Button(
        onClick = {
            //context.startActivity(intent)
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { color = if (it.isFocused) Green else Black }
            .focusModifier()
            .pointerInput(Unit) { detectTapGestures { focusRequester.requestFocus() } }
            .clickable { checked = !checked }
            .padding(16.dp)
            .width(220.dp)
            .height(80.dp),

        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = Color.Red)

    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            text = buttonText,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}



@Composable
fun Greeting(name: String) {
    Column(Modifier.padding(100.dp)) {
        Text(
            text = "Fitness $name!",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
        )
        Divider(color = Color.White)
        ButtonComponent(
            buttonText = "Шагомер"
        )
        Divider(color = Color.White)
        ButtonComponent(
            buttonText = "Личный тренер"
        )
        Divider(color = Color.White)
        ButtonComponent(
            buttonText = "Ежедневник"
        )
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitnessComposeTheme {
        Greeting("Everyday Trainer")
    }
}