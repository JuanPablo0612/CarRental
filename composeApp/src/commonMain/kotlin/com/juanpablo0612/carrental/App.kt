package com.juanpablo0612.carrental

import androidx.compose.runtime.Composable
import com.juanpablo0612.carrental.navigation.AppNavigation
import com.juanpablo0612.carrental.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(appModule)
        }
    ) {
        AppTheme {
            AppNavigation()
        }
    }
}