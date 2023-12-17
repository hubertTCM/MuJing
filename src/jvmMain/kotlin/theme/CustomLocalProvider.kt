import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import player.LocalAudioPlayerComponent
import player.isMacOS
import player.rememberAudioPlayerComponent


@Composable
fun CustomLocalProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalAudioPlayerComponent provides rememberAudioPlayerComponent(),
        LocalCtrl provides rememberCtrl(),
        LocalTextSelectionColors provides textSelectionColors(),
        LocalScrollbarStyle provides scrollbarStyle(),
        content = content
    )
}

val LocalCtrl = staticCompositionLocalOf<String> {
    error("LocalCtrl isn't provided")
}

/** 本地的 Ctrl 键 */
@Composable
fun rememberCtrl(): String = remember {
    if (isMacOS()) "⌃" else "Ctrl"
}

/** 选择字符时的背景颜色 */
fun textSelectionColors(): TextSelectionColors {
    val defaultSelectionColor = Color(0xFF4286F4)
    val backgroundColor = defaultSelectionColor.copy(alpha = 0.4f)
    return TextSelectionColors(handleColor = defaultSelectionColor, backgroundColor = backgroundColor)
}

fun scrollbarStyle(): ScrollbarStyle {
    val shape = if(isMacOS()) RoundedCornerShape(4.dp) else  RectangleShape
    return ScrollbarStyle(
        minimalHeight = 16.dp,
        thickness = 8.dp,
        shape =shape,
        hoverDurationMillis = 300,
        unhoverColor = Color.Black.copy(alpha = 0.12f),
        hoverColor = Color.Black.copy(alpha = 0.50f)
    )
}