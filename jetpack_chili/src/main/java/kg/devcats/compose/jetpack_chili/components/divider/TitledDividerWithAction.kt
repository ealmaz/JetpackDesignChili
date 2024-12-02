package kg.devcats.compose.jetpack_chili.components.divider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.clickableWithoutEffect
import kg.devcats.compose.jetpack_chili.theme.Chili

/**
 * Заголовок для элементов с кнопкой действия
 * @param modifier Стиль элемента
 * @param params Параметры отображения заголовок (Строится при помощи TitledDividerDefaults)
 * @param startPlaceholder Переднее пространство для отображения элементов
 * @param endActionList принимает список TitledDividerActionType с параметрами для отображения элементов
 * @sample TitledDividerActionType
 */
@Composable
fun TitledDividerWithAction(
    modifier: Modifier = Modifier,
    params: TitleDividerParams,
    startPlaceholder: @Composable () -> Unit = {},
    endActionList: List<TitledDividerActionType> = listOf()
) {
    TitledDivider(
        modifier = modifier,
        params = params,
        startPlaceholder = startPlaceholder,
        endPlaceholder = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                endActionList.forEach { actionType ->
                    when (actionType) {
                        is TitledDividerActionType.Icon -> {
                            TitledDividerIconAction(
                                params = actionType,
                                onActionClick = actionType.onActionClick
                            )
                        }
                        is TitledDividerActionType.Text -> {
                            TitledDividerTextAction(
                                params = actionType,
                                onActionClick = actionType.onActionClick
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun TitledDividerTextAction(
    params: TitledDividerActionType.Text,
    onActionClick: () -> Unit = {}
) {
    Text(
        modifier = params.modifier.clickableWithoutEffect { onActionClick.invoke() },
        text = params.text,
        style = params.style ?: Chili.typography.H16_Action_500,
        maxLines = params.maxLines,
    )
}

@Composable
private fun TitledDividerIconAction(
    params: TitledDividerActionType.Icon,
    onActionClick: () -> Unit = {}
) {
    Image(
        painter = params.icon,
        contentDescription = "End Icon",
        modifier = params.modifier
            .padding(end = 6.dp)
            .clickableWithoutEffect(onClick = onActionClick)
    )
}

sealed interface TitledDividerActionType {
    data class Icon(
        val modifier: Modifier = Modifier,
        val icon: Painter,
        val onActionClick: () -> Unit
    ) : TitledDividerActionType

    data class Text(
        val modifier: Modifier = Modifier,
        val text: String,
        val maxLines: Int = 2,
        val style: TextStyle? = null,
        val onActionClick: () -> Unit
    ) : TitledDividerActionType
}