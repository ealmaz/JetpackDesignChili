package kg.devcats.compose.jetpack_chili.components.pdf_viewer

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kg.devcats.compose.jetpack_chili.R
import kg.devcats.compose.jetpack_chili.components.navigation.ChiliCenteredAppToolbar
import kg.devcats.compose.jetpack_chili.shareFile

@Composable
fun PdfViewer(
    modifier: Modifier = Modifier,
    title: String,
    shareIsVisible: Boolean = false,
    shareTitle: String? = null,
    isDividerVisible: Boolean = true,
    isNavigationIconVisible: Boolean = true,
    onNavigationIconClick: () -> Unit,
    pdfFileCategory: PdfLoadCategory,
    errorPdfLoadText: String,
    errorDialogCloseText: String,
) {
    val context = LocalContext.current
    var pdfUri by remember { mutableStateOf(Uri.EMPTY) }

    Column(modifier = modifier.fillMaxSize()) {
        ChiliCenteredAppToolbar(
            title = title,
            isDividerVisible = isDividerVisible,
            isNavigationIconVisible = isNavigationIconVisible,
            onNavigationIconClick = onNavigationIconClick,
            endFrame = {
                AnimatedVisibility(
                    modifier = Modifier.padding(end = 16.dp),
                    visible = shareIsVisible && (pdfUri != Uri.EMPTY)
                ) {
                    Image(
                        modifier = Modifier.clickable {
                            context.shareFile(
                                uri = pdfUri,
                                fileType = "application/pdf",
                                title = shareTitle ?: ""
                            )
                        },
                        painter = painterResource(R.drawable.chili_ic_share),
                        contentDescription = null
                    )
                }
            }
        )

        PdfViewerComponent(
            modifier = modifier.fillMaxSize(),
            pdfFile = pdfFileCategory,
            errorText = errorPdfLoadText,
            closeDialogButtonText = errorDialogCloseText,
            errorDialogIsClosed = onNavigationIconClick,
            fileUploaded = { uri -> pdfUri = uri }
        )
    }
}