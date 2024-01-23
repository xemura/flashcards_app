package com.xenia.englishusingflashcards.presentation.learning_screen.component_swipe_card

import android.annotation.SuppressLint
import android.graphics.drawable.Animatable
import android.util.Log
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import com.xenia.englishusingflashcards.presentation.learning_screen.component_swipe_card.Constants.cardHeight
import com.xenia.englishusingflashcards.presentation.learning_screen.component_swipe_card.Constants.paddingOffset
import com.xenia.englishusingflashcards.room.entities.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun SwipeableCard(
    dataSource: List<Int>
) {
    val visibleCard: Int = StrictMath.min(1, dataSource.size)
    val scope = rememberCoroutineScope()
    val firstCard = remember {
        mutableStateOf(0)
    }
    val offset: androidx.compose.animation.core.Animatable<Offset, AnimationVector2D> = remember {
        androidx.compose.animation.core.Animatable(
            Offset(0f, 0f),
            Offset.VectorConverter
        )
    }
    val animationSpec : FiniteAnimationSpec<Offset> = tween(
        durationMillis = 150,
        easing = LinearEasing
    )

    fun rearrangeForward() {
        if (firstCard.value == dataSource.size - 1) {
            firstCard.value = 0
        } else firstCard.value++

        Log.d("SwipeableCard", "rearrangeForward")
    }

    Box(Modifier.fillMaxWidth()) {
        repeat(visibleCard) { index ->
            val zIndex = Constants.TOP_Z_INDEX - index
            val scale = calculateScale(index)
            val offsetY = calculateOffset(index)
            val cardModifier =
                makeCardModifier(
                    scope = scope,
                    cardIndex = index,
                    scale = scale,
                    zIndex = zIndex,
                    offsetY = offsetY,
                    offset = offset,
                    rearrangeForward = { rearrangeForward() },
                    animationSpec = animationSpec
                )

            CardContent(
                modifier = cardModifier,
                cardIndex = if (index == 0) firstCard.value else firstCard.value + 1
            )
        }
    }
}

private fun calculateScale(idx: Int) : Float {
    return when (idx) {
        1 -> 0.97f
        2 -> 0.94f
        else -> 1f
    }
}

private fun calculateOffset(idx: Int) : Int {
    return when (idx) {
        1 -> -(paddingOffset * idx * 1.1).toInt()
        2 -> -(paddingOffset * idx * 1.1).toInt()
        else -> -paddingOffset.toInt()
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
fun makeCardModifier(
    scope: CoroutineScope,
    cardIndex: Int,
    scale: Float,
    zIndex: Float,
    offset: androidx.compose.animation.core.Animatable<Offset, AnimationVector2D>,
    animationSpec: FiniteAnimationSpec<Offset>,
    offsetY: Int,
    rearrangeForward: () -> Unit,
): Modifier {
    return if (cardIndex > Constants.TOP_CARD_INDEX) Modifier
        .graphicsLayer {
            translationY =
                if (offset.value.y != 0f) min(
                    abs(offset.value.y),
                    paddingOffset * 1.1f
                ) else 0f
            scaleY = if (offset.value.y != 0f) {
                min(scale + (abs(offset.value.y) / 1000), 1.06f - (cardIndex * 0.03f))
            } else scale
        }
        .scale(scale)
        .offset { IntOffset(0, offsetY) }
        .zIndex(zIndex)
        .fillMaxWidth()
        .height(cardHeight)
    else Modifier
        .scale(scale)
        .offset { IntOffset(0, offset.value.y.roundToInt()) }
        .zIndex(zIndex)
        .fillMaxWidth()
        .height(cardHeight)
        .pointerInput(Unit) {
            // если нажать на карточку то она опустится вниз на -600;600
            detectTapGestures(
                onTap = {
                    scope.launch {
//                        rearrangeBackward()
                        offset.animateTo(
                            targetValue = Offset(-600f, 600f),
                            animationSpec = snap()
                        )
                        offset.animateTo(
                            targetValue = Offset(0f, 0f),
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearEasing
                            )
                        )
                    }
                }
            )
        }
        .pointerInput(Unit) {
            detectDragGestures { change, _ ->
                val dragOffset = Offset(
                    0.0f,
                    offset.value.y + change.positionChange().y
                )
                scope.launch {
                    offset.snapTo(dragOffset)
                    if (change.positionChange() != Offset.Zero) change.consume()

                    val y = when {
                        offset.value.y > 250 -> size.height.toFloat()
                        offset.value.y < -250 -> -size.height.toFloat()
                        else -> 0f
                    }

                    offset.animateTo(
                        targetValue = Offset(0.0f, y),
                        animationSpec = animationSpec
                    )
                    if (abs(offset.value.x) == size.width.toFloat() || abs(offset.value.y) == size.height.toFloat()) {
                        rearrangeForward()
                        offset.animateTo(
                            targetValue = Offset(0f, 0f),
                            animationSpec = snap()
                        )
                    }
                }
            }
        }
}




















