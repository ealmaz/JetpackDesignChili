package kg.devcats.compose.jetpack_chili.components.pin

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

sealed interface PinAnimations {
    class ErrorShake(
        private val strength: Float = 17f,
        private val finished: () -> Unit = {}
    ) : PinAnimations {
        val xPosition = Animatable(0f)

        suspend fun startAnim(animationDuration: Int = 50, repeat: Int = 3) {
            val shakeAnimationSpec: AnimationSpec<Float> = tween(animationDuration)
            repeat(repeat) {
                xPosition.animateTo(-strength, shakeAnimationSpec)
                xPosition.animateTo(0f, shakeAnimationSpec)
            }
            finished()
        }

        suspend fun reset() = xPosition.snapTo(0f)
    }

    class SuccessShake(
        private val shortStrength: Float = 10f,
        private val longStrength: Float = 15f,
        private val finished: () -> Unit = {}
    ) : PinAnimations {
        val shortPosition = Animatable(0f)
        val longPosition = Animatable(0f)

        suspend fun startAnim(animationDuration: Int = 200, repeat: Int = 1) = coroutineScope {
            val shakeAnimationSpec: AnimationSpec<Float> = tween(animationDuration)
            repeat(repeat) {
                val shortAnim = async {
                    shortPosition.animateTo(-shortStrength, shakeAnimationSpec)
                    shortPosition.animateTo(shortStrength, shakeAnimationSpec)
                    shortPosition.animateTo(0f, shakeAnimationSpec)
                }
                val longAnim = async {
                    longPosition.animateTo(-longStrength, shakeAnimationSpec)
                    longPosition.animateTo(longStrength, shakeAnimationSpec)
                    longPosition.animateTo(0f, shakeAnimationSpec)
                }

                shortAnim.await()
                longAnim.await()
            }
            finished()
        }

        suspend fun reset() {
            shortPosition.snapTo(0f)
            longPosition.snapTo(0f)
        }
    }
}