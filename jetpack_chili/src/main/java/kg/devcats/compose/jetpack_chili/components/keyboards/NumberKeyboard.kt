package kg.devcats.compose.jetpack_chili.components.keyboards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.theme.Chili

sealed class KeyboardKeyType {
    class Symbol(val value: Char) : KeyboardKeyType()
    class MultipleKeys(val set: List<Char>, val isSymbols: Boolean = true) : KeyboardKeyType()
    data object Empty : KeyboardKeyType()
    data object Backspace : KeyboardKeyType()
}

@Composable
fun NumberKeyboard(
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue,
    maxLength: Int? = null,
    onInputChanged: (TextFieldValue) -> Unit,
    specialSymbols: List<Char> = emptyList()
) {

    var keys by remember {
        mutableStateOf(generateNumberKeys(specialSymbols))
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(keys) { key ->
            KeyButton(
                key = key,
                modifier = Modifier,
                onKeyClick = { keyType ->
                    when (keyType) {
                        is KeyboardKeyType.MultipleKeys -> {
                            if (keyType.isSymbols) {
                                keys = generateSymbolKeys(keyType.set)
                            } else {
                                keys = generateNumberKeys(keyType.set)
                            }
                            return@KeyButton
                        }

                        KeyboardKeyType.Backspace -> {
                            if (textFieldValue.selection.min == 0) return@KeyButton
                            val newText = StringBuilder(textFieldValue.text)
                            newText.deleteCharAt(textFieldValue.selection.max - 1)
                            var selection = textFieldValue.selection
                            if (selection.max < newText.length) {
                                selection = TextRange(selection.max - 1)
                            }
                            onInputChanged.invoke(textFieldValue.copy(text = newText.toString(), selection = selection))
                        }
                        is KeyboardKeyType.Symbol -> {
                            val newText = StringBuilder(textFieldValue.text)
                            if (newText.length == maxLength) return@KeyButton
                            newText.insert(textFieldValue.selection.max, keyType.value)

                            var selection = textFieldValue.selection
                            if (selection == TextRange(textFieldValue.text.length)) {
                                selection = TextRange(textFieldValue.text.length + 1)
                            } else {
                                selection = TextRange(selection.max + 1)
                            }
                            onInputChanged.invoke(textFieldValue.copy(text = newText.toString(), selection = selection))
                        }
                        else -> {}
                    }
                },
            )
        }
    }
}

private fun generateSymbolKeys(specialSymbols: List<Char>): List<KeyboardKeyType> {
    val symbols: List<Char> = specialSymbols.drop(1)
    val symbolKeys = symbols.map {
        KeyboardKeyType.Symbol(it)
    }
    val reversedList = mutableListOf(
        KeyboardKeyType.Backspace,
        KeyboardKeyType.Symbol(specialSymbols.first()),
        KeyboardKeyType.MultipleKeys(set = specialSymbols, isSymbols = false),
    ).apply {
        addAll(symbolKeys)
    }

    return reversedList.padEnd(12, KeyboardKeyType.Empty).reversed()
}

private fun <T> MutableList<T>.padEnd(size: Int, defaultValue: T): MutableList<T> {
    while (this.size < size) {
        this.add(defaultValue)
    }
    return this
}

private fun generateNumberKeys(specialSymbols: List<Char>): List<KeyboardKeyType> {
    val actionBtn = when (specialSymbols.size) {
        0 -> KeyboardKeyType.Empty
        1 -> KeyboardKeyType.Symbol(specialSymbols.first())
        else -> KeyboardKeyType.MultipleKeys(specialSymbols, isSymbols = true)
    }
    return listOf(
        KeyboardKeyType.Symbol('1'),
        KeyboardKeyType.Symbol('2'),
        KeyboardKeyType.Symbol('3'),
        KeyboardKeyType.Symbol('4'),
        KeyboardKeyType.Symbol('5'),
        KeyboardKeyType.Symbol('6'),
        KeyboardKeyType.Symbol('7'),
        KeyboardKeyType.Symbol('8'),
        KeyboardKeyType.Symbol('9'),
        actionBtn,
        KeyboardKeyType.Symbol('0'),
        KeyboardKeyType.Backspace
    )
}

@Composable
fun KeyButton(
    modifier: Modifier = Modifier,
    key: KeyboardKeyType,
    isEnabled: Boolean = key != KeyboardKeyType.Empty,
    onKeyClick: (KeyboardKeyType) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Chili.color.buttonAdditionalContainer)
            .clickable(enabled = isEnabled, onClick = { onKeyClick.invoke(key) })
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        when (key) {
            is KeyboardKeyType.Symbol -> {
                TextKeyButton(text = key.value.toString())
            }

            KeyboardKeyType.Backspace -> {
                DrawableKeyButton(resId = kg.devcats.compose.jetpack_chili.R.drawable.ic_backspace_btn)
            }

            is KeyboardKeyType.MultipleKeys -> {
                val text = if (key.isSymbols) {
                    key.set.take(3).joinToString()
                } else {
                    "123"
                }
                TextKeyButton(text = text)
            }

            else -> {
                TextKeyButton()
            }
        }
    }
}

@Composable
fun BoxScope.TextKeyButton(text: String = "") {
    Text(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .align(Alignment.Center),
        text = text,
        style = Chili.typography.H20,
        color = Chili.color.keyColor
    )
}

@Composable
fun BoxScope.DrawableKeyButton(resId: Int) {
    Icon(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .align(Alignment.Center),
        painter = painterResource(id = resId),
        contentDescription = null,
        tint = Chili.color.keyColor
    )
}