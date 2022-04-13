package com.barry.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.barry.example.ui.theme.ExampleTheme
import com.barry.example.ui.wiget.AnimNavTopAppBar
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ExampleTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          MainScreen()
        }
      }
    }
  }
}


@OptIn(ExperimentalAnimationApi::class)
@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
  var showNavIcon by remember { mutableStateOf(false) }
  var title by remember { mutableStateOf("page1") }

  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
    Column {
      val navController = rememberAnimatedNavController()

      AnimNavTopAppBar(
        title = title,
        showNavIcon = showNavIcon,
        navOnClick = {
          when (title) {
            "page2" -> {
              title = "page1"
              showNavIcon = false
              navController.popBackStack()
            }
            "page3" -> {
              title = "page2"
              navController.popBackStack()
            }
          }
        }
      )

      AnimatedNavHost(navController = navController, startDestination = "page1") {
        composable(
          route = "page1",
          enterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Left,
              animationSpec = tween(durationMillis)
            )
          },
          exitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Left,
              animationSpec = tween(durationMillis)
            )
          },
          popEnterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(durationMillis)
            )
          },
          popExitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(durationMillis)
            )
          }
        ) {
          Page1 {
            title = "page2"
            showNavIcon = true
            navController.navigate("page2")
          }
        }

        composable(route = "page2",
          enterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Left,
              animationSpec = tween(durationMillis)
            )
          },
          exitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Left,
              animationSpec = tween(durationMillis)
            )
          },
          popEnterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(durationMillis)
            )
          },
          popExitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(durationMillis)
            )
          }) {
          Page2 {
            title = "page3"
            showNavIcon = true
            navController.navigate("page3")
          }
        }

        composable(route = "page3",
          enterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Left,
              animationSpec = tween(durationMillis)
            )
          },
          exitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Left,
              animationSpec = tween(durationMillis)
            )
          },
          popEnterTransition = {
            slideIntoContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(durationMillis)
            )
          },
          popExitTransition = {
            slideOutOfContainer(
              AnimatedContentScope.SlideDirection.Right,
              animationSpec = tween(durationMillis)
            )
          }) {
          Page3()
        }
      }
    }
  }
}

@Composable
fun Page1(click: () -> Unit = {}) {
  Box(
    Modifier
      .fillMaxSize()
      .background(Color.Green), Alignment.Center
  ) {
    Text(text = "page1", modifier = Modifier.clickable { click() }, fontSize = 24.sp)
  }
}

@Composable
fun Page2(click: () -> Unit = {}) {
  Box(
    Modifier
      .fillMaxSize()
      .background(Color.Magenta), Alignment.Center
  ) {
    Text(text = "page2", modifier = Modifier.clickable { click() }, fontSize = 24.sp)
  }
}

@Composable
fun Page3(click: () -> Unit = {}) {
  Box(
    Modifier
      .fillMaxSize()
      .background(Color.Red), Alignment.Center
  ) {
    Text(text = "page3", modifier = Modifier.clickable { click() }, fontSize = 24.sp)
  }
}

const val durationMillis: Int = 300
