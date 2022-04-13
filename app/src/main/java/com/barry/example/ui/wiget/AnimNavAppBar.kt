package com.barry.example.ui.wiget

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimNavTopAppBar(
  modifier: Modifier = Modifier,
  title: String = "",
  imageVector: ImageVector = Icons.Default.ArrowBack,
  showNavIcon: Boolean = false,
  navOnClick: () -> Unit = {},
  navAlpha: Float = 1f
) {
  TopAppBar(modifier = modifier) {
    Row(
      Modifier
        .fillMaxWidth()
        .weight(1f),
      verticalAlignment = Alignment.CenterVertically
    ) {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
        AnimatedVisibility(visible = showNavIcon) {
          IconButton(
            onClick = navOnClick,
            Modifier
              .alpha(navAlpha)
              .padding(end = 8.dp)
          ) {
            Icon(imageVector = imageVector, contentDescription = null)
          }
        }

        ProvideTextStyle(value = MaterialTheme.typography.h6) {
          AnimatedContent(
            targetState = title,
            transitionSpec = {
              if (targetState < initialState) {
                slideInHorizontally(
                  animationSpec = tween(
                    durationMillis = durationMillis
                  )
                ) { width -> -width } + fadeIn() with
                        slideOutHorizontally(
                          animationSpec = tween(
                            durationMillis = durationMillis
                          )
                        ) { width -> width } + fadeOut()
              } else {
                slideInHorizontally(
                  animationSpec = tween(
                    durationMillis = durationMillis
                  )
                ) { width -> width } + fadeIn() with
                        slideOutHorizontally(
                          animationSpec = tween(
                            durationMillis = durationMillis
                          )
                        ) { width -> -width } + fadeOut()
              }.using(
                SizeTransform(clip = false)
              )
            }
          ) {
            Text(
              text = title,
              Modifier
                .alpha(navAlpha)
                .padding(start = 12.dp)
            )
          }
        }
      }
    }
  }
}

const val durationMillis: Int = 300