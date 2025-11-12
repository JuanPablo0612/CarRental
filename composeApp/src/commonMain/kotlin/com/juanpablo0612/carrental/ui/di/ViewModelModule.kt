package com.juanpablo0612.carrental.ui.di

import com.juanpablo0612.carrental.ui.vehicles.detail.VehicleDetailViewModel
import com.juanpablo0612.carrental.ui.vehicles.list.VehicleListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::VehicleListViewModel)
    viewModelOf(::VehicleDetailViewModel)
}