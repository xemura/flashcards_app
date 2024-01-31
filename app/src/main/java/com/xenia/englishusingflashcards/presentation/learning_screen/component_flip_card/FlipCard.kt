package com.xenia.englishusingflashcards.presentation.learning_screen.component_flip_card

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ), label = ""
    )
    Card(
        modifier = modifier
            .graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation.value
                } else {
                    rotationY = rotation.value
                }
                cameraDistance = 12f * density
            },
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { onClick(cardFace) },

    ) {
        if (rotation.value <= 90f) {
            Box(
                Modifier.fillMaxSize()
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        if (axis == RotationAxis.AxisX) {
                            rotationX = 180f
                        } else {
                            rotationY = 180f
                        }
                    },
            ) {
                back()
            }
        }
    }
}

//@Composable
//fun FlipCard() {
//
//    var rotated by remember { mutableStateOf(false) }
//
//    val rotation by animateFloatAsState(
//        targetValue = if (rotated) 180f else 0f,
//        animationSpec = tween(500), label = ""
//    )
//
//    val animateFront by animateFloatAsState(
//        targetValue = if (!rotated) 1f else 0f,
//        animationSpec = tween(500), label = ""
//    )
//
//    val animateBack by animateFloatAsState(
//        targetValue = if (rotated) 1f else 0f,
//        animationSpec = tween(500), label = ""
//    )
//
//    val animateColor by animateColorAsState(
//        targetValue = if (rotated) Color.Red else Color.Blue,
//        animationSpec = tween(500), label = ""
//    )
//
//    Box(
//        Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxSize(.5f)
//                .graphicsLayer {
//                    rotationY = rotation
//                    cameraDistance = 8 * density
//                }
//                .clickable {
//                    rotated = !rotated
//                },
//            colors = CardDefaults.cardColors(
//                containerColor = animateColor
//            )
//        ) {
//            Column(
//                Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//
//                Text(text = if (rotated) "Back" else "Front",
//                    modifier = Modifier
//                        .graphicsLayer {
//                            alpha = if (rotated) animateBack else animateFront
//                            rotationY = rotation
//                        })
//            }
//
//        }
//    }
//}