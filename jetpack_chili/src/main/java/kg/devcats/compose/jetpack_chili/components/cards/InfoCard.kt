package kg.devcats.compose.jetpack_chili.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliAdditionalButton
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    titleStyle: TextStyle = Chili.typography.H16_Primary_700,
    subtitleStyle: TextStyle = Chili.typography.H14_Primary,
    buttonConfig: InfoCardButtonConfig? = null,
    horizontalButtons: Pair<InfoCardButtonConfig, InfoCardButtonConfig>? = null,
    content: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = titleStyle,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = subtitle,
            style = subtitleStyle,
            textAlign = TextAlign.Center
        )

        content?.invoke()

        buttonConfig?.let { config ->
            Spacer(modifier = Modifier.height(16.dp))
            when (config.type) {
                InfoCardButtonType.PRIMARY -> {
                    ChiliPrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = config.text,
                        onClick = config.onClick
                    )
                }

                InfoCardButtonType.ADDITIONAL -> {
                    ChiliAdditionalButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = config.text,
                        onClick = config.onClick
                    )
                }
            }
        }

        horizontalButtons?.let { (leftButton, rightButton) ->
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when (leftButton.type) {
                    InfoCardButtonType.PRIMARY -> {
                        ChiliPrimaryButton(
                            text = leftButton.text,
                            onClick = leftButton.onClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    InfoCardButtonType.ADDITIONAL -> {
                        ChiliAdditionalButton(
                            text = leftButton.text,
                            onClick = leftButton.onClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                when (rightButton.type) {
                    InfoCardButtonType.PRIMARY -> {
                        ChiliPrimaryButton(
                            text = rightButton.text,
                            onClick = rightButton.onClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    InfoCardButtonType.ADDITIONAL -> {
                        ChiliAdditionalButton(
                            text = rightButton.text,
                            onClick = rightButton.onClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

data class InfoCardButtonConfig(
    val text: String,
    val type: InfoCardButtonType = InfoCardButtonType.PRIMARY,
    val onClick: () -> Unit
)

enum class InfoCardButtonType {
    PRIMARY,
    ADDITIONAL
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoCard() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
        InfoCard(
            title = "Title",
            subtitle = "Subtitle",
            buttonConfig = InfoCardButtonConfig(
                text = "Primary Button",
                type = InfoCardButtonType.PRIMARY,
                onClick = {}
            ),
            horizontalButtons = Pair(
                InfoCardButtonConfig(
                    text = "Additional",
                    type = InfoCardButtonType.ADDITIONAL,
                    onClick = {}
                ),
                InfoCardButtonConfig(
                    text = "Primary",
                    type = InfoCardButtonType.PRIMARY,
                    onClick = {}
                )
            ),
            content = {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Additional Content Here",
                    style = Chili.typography.H14_Primary,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}