package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.buttons.ChiliPrimaryButton
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.sharePdfFile
import kg.devcats.compose.jetpack_chili.theme.Chili

@Composable
fun PdfViewer(
    modifier: Modifier = Modifier,
    title: String = "",
    shareIsVisible: Boolean = false,
    shareTitle: String? = null,
    isDividerVisible: Boolean = true,
    isNavigationIconVisible: Boolean = true,
    zoomIsEnabled: Boolean = true,
    onNavigationIconClick: () -> Unit,
    pdfSourceCategory: PdfSourceCategory,
    errorPdfLoadText: String = stringResource(R.string.error_pdf_loading),
    closeDialogButtonText: String = stringResource(R.string.ok),
    buttonShareIsVisible: Boolean = false,
    toolbarBackgroundColor: Color = Chili.color.toolbarBackground,
    navigationIcon: Painter = painterResource(id = R.drawable.chili4_ic_back_arrow_rounded),
    isEdgeToEdgeEnabled: Boolean = false
) {
    val context = LocalContext.current
    var pdfUri by remember { mutableStateOf(Uri.EMPTY) }
    val density = LocalDensity.current.density
    var paddingBottom by remember { mutableStateOf(0.dp) }

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Column {
                if (isEdgeToEdgeEnabled) {
                    Spacer(
                        modifier = Modifier
                            .background(toolbarBackgroundColor)
                            .fillMaxWidth()
                            .windowInsetsTopHeight(WindowInsets.safeDrawing)
                    )
                }

                ChiliCenteredAppToolbar(
                    title = title,
                    isDividerVisible = isDividerVisible,
                    isNavigationIconVisible = isNavigationIconVisible,
                    onNavigationIconClick = onNavigationIconClick,
                    navigationIcon = navigationIcon,
                    backgroundColor = toolbarBackgroundColor,
                    endFrame = {
                        AnimatedVisibility(
                            modifier = Modifier.padding(end = 16.dp),
                            visible = shareIsVisible && (pdfUri != Uri.EMPTY)
                        ) {
                            Image(
                                modifier = Modifier.clickable {
                                    context.sharePdfFile(
                                        pdfUri,
                                        shareTitle
                                    )
                                },
                                painter = painterResource(R.drawable.chili_ic_share),
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            PdfViewerComponent(
                modifier = modifier.fillMaxSize(),
                pdfSourceCategory = pdfSourceCategory,
                errorText = errorPdfLoadText,
                zoomIsEnabled = zoomIsEnabled,
                closeDialogButtonText = closeDialogButtonText,
                onErrorDialogIsClosed = onNavigationIconClick,
                onFileUploaded = { uri -> pdfUri = uri },
                isEdgeToEdgeEnabled = isEdgeToEdgeEnabled,
                buttonShareIsVisible = buttonShareIsVisible
            )
        }
        if (buttonShareIsVisible) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Chili.color.pdfBackgroundColor)
                    .onGloballyPositioned {
                        paddingBottom = it.size.height.dp / density
                    }
                    .align(Alignment.BottomCenter)
            ) {
                ChiliPrimaryButton(
                    enabled = pdfUri != Uri.EMPTY,
                    text = stringResource(R.string.save_or_share),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 12.dp,
                            bottom = if (isEdgeToEdgeEnabled) WindowInsets.safeDrawing.asPaddingValues()
                                .calculateBottomPadding() + 16.dp else 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    context.sharePdfFile(pdfUri, shareTitle)
                }
            }
        }
    }
}