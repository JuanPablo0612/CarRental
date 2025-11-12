package com.juanpablo0612.carrental.data.di

import com.juanpablo0612.carrental.data.vehicles.FakeVehiclesRepository
import com.juanpablo0612.carrental.data.vehicles.VehiclesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf<VehiclesRepository>(::FakeVehiclesRepository)
}