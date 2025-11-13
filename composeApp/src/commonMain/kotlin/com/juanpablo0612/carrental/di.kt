package com.juanpablo0612.carrental

import com.juanpablo0612.carrental.data.di.dataModule
import com.juanpablo0612.carrental.ui.di.viewModelModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule, viewModelModule)
}