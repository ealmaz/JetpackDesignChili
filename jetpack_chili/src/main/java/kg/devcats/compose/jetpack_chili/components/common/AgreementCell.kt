package kg.devcats.compose.jetpack_chili.components.common

import android.text.Spannable
import android.text.style.URLSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.theme.Chili


@Composable
fun AgreementCell(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: Boolean = false,
    isEditable: Boolean = true,
    icon: Painter? = painterResource(id = R.drawable.chili_ic_checkmark),
    linkColor: Color = Chili.color.linkText,
    containerBackgroundColor: Color = Chili.color.surfaceBackground,
    onCheckedChange: (Boolean) -> Unit = {},
    action: (url: String) -> (Unit)
) {
    var checkboxState by remember { mutableStateOf(isChecked) }

    Surface(
        color = containerBackgroundColor,
        contentColor = Color.Unspecified,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isEditable) {
                ChiliCheckBox(
                    checked = checkboxState,
                    onCheckedChange = {
                        checkboxState = it
                        onCheckedChange(it)
                    }
                )
            } else {
                icon?.let {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }

            ClickableLinkText(title = title, action = action, linkColor = linkColor)

        }
    }
}


@Composable
fun ClickableLinkText(
    modifier: Modifier = Modifier,
    title: String,
    linkColor: Color = Chili.color.linkText,
    action: (url: String) -> (Unit),
    style: TextStyle = Chili.typography.H14_Primary,
    linkFontSize: TextUnit = TextUnit(14f, TextUnitType.Sp)
) {
    val spannedText = HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_LEGACY)
    val annotatedString = buildAnnotatedString {
        val spannable = spannedText as? Spannable ?: return@buildAnnotatedString
        val urlSpans = spannable.getSpans(0, spannable.length, URLSpan::class.java)
        append(spannedText.toString())
        urlSpans.forEach { span ->
            val start = spannable.getSpanStart(span)
            val end = spannable.getSpanEnd(span)

            addStringAnnotation(
                tag = "URL",
                annotation = span.url,
                start = start,
                end = end
            )

            addStyle(
                style = SpanStyle(color = linkColor, textDecoration = TextDecoration.Underline, fontSize = linkFontSize),
                start = start,
                end = end
            )
        }
    }

    ClickableText(
        style = style,
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()?.let { annotation ->
                action(annotation.item)
            }
        },
        modifier = modifier
    )
}