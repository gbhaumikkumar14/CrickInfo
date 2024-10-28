package com.example.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.presentation.R
import com.example.presentation.theme.LocalDimens

@Composable
fun NetworkErrorRetryView(
    message: String = stringResource(id = R.string.something_went_wrong),
    onRetryClick: () -> Unit
) {
    val dimens = LocalDimens.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(onClick = onRetryClick) {
            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.baseline_retry_24),
                contentDescription = stringResource(id = R.string.reload_image_button),
                modifier = Modifier
                    .width(dimens.custom24)
                    .height(dimens.custom24)
            )
        }
    }
}