package org.wordpress.android.ui.main.jetpack.migration.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import org.wordpress.android.R
import org.wordpress.android.ui.compose.components.ColumnWithFrostedGlassBackground
import org.wordpress.android.ui.compose.utils.uiStringText
import org.wordpress.android.ui.main.jetpack.migration.JetpackMigrationViewModel.SiteListItemUiState
import org.wordpress.android.ui.main.jetpack.migration.JetpackMigrationViewModel.StepUiState

@Composable
fun WelcomeStep(uiState: StepUiState.Welcome) = with(uiState) {
    ScreenIcon(iconRes = screenIconRes)
    Title(text = uiStringText(title))
    Subtitle(text = uiStringText(subtitle))
    Message(text = uiStringText(message))

    Box {
        val listState = rememberLazyListState()
        val blurredListState = rememberLazyListState()

        SiteList(
                items = sites,
                listState = listState,
        )
        ButtonsPanel(sites, blurredListState) {
            PrimaryButton(
                    text = uiStringText(primaryActionButton.text),
                    onClick = primaryActionButton.onClick,
            )
            SecondaryButton(
                    text = uiStringText(secondaryActionButton.text),
                    onClick = secondaryActionButton.onClick,
            )
        }
        ListStateSync(listState, blurredListState)
    }
}

@Composable
private fun ListStateSync(
    source: LazyListState,
    target: LazyListState,
) = LaunchedEffect(source.firstVisibleItemIndex, target.firstVisibleItemScrollOffset) {
    target.scrollToItem(
            source.firstVisibleItemIndex,
            source.firstVisibleItemScrollOffset,
    )
}

@Composable
private fun ButtonsPanel(
    items: List<SiteListItemUiState>,
    blurredListState: LazyListState,
    content: @Composable () -> Unit,
) {
    ColumnWithFrostedGlassBackground(
            blurRadius = 4.dp,
            backgroundColor = colorResource(R.color.bg_jp_migration_buttons_panel),
            borderColor = colorResource(R.color.gray_10).copy(alpha = 0.5f),
            background = { clipModifier, blurModifier ->
                SiteList(
                        items = items,
                        listState = blurredListState,
                        userScrollEnabled = false,
                        modifier = clipModifier,
                        blurModifier = blurModifier,
                )
            },
            content = content
    )
}
