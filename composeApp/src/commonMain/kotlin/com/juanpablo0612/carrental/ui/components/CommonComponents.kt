package com.juanpablo0612.carrental.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.BoxScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledOutlinedTextField(
    state: TextFieldState,
    label: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: (performDefaultAction: () -> Unit) -> Unit = { it() }
) {
    OutlinedTextField(
        state = state,
        label = { label() },
        placeholder = { placeholder() },
        modifier = modifier,
        enabled = enabled,
        isError = isError,
        supportingText = supportingText?.let { { it() } },
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction
    )
}

@Composable
fun PrimaryActionButton(
    text: String,
    isLoading: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    heightDp: Int = 56
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(heightDp.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.height(24.dp))
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun BoxScope.CenteredCircularProgress(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
}
