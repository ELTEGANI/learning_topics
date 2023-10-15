package com.example.learning_topics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.learning_topics.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
               MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
     if(shouldShowOnBoarding){
        OnBoardingScreen(onContinuedClicked = {shouldShowOnBoarding=false})
     }else{
         Greetings()
     }

  }


@Composable
private  fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String>  = List(1000){"$it"}
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
       items(items=names){name->
           Greeting(name = name)
       }
    }
}
@Composable
 fun Greeting(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if(expanded) 50.dp else 0.dp, label = "animation",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(
                    bottom = extraPadding.coerceAtLeast(0.dp)
                )) {
                Text(text = "Hello")
                Text(text = "$name!", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
                    , contentDescription = ""

                )
            }
        }
    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier=Modifier,
                     onContinuedClicked:()->Unit) {
  Column(
      modifier = modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
  ) {
     Text(text = "Welcome To The Basic")
     Button(
         modifier = Modifier.padding(vertical = 24.dp),
         onClick = onContinuedClicked) {
         Text(text = "Continue")
     } 
  }
}
