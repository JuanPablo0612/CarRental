package com.juanpablo0612.carrental.data.di

import com.juanpablo0612.carrental.data.vehicles.VehiclesRepository
import com.juanpablo0612.carrental.data.vehicles.VehiclesRepositoryImpl
import com.juanpablo0612.carrental.data.vehicles.remote.VehiclesRemoteDataSource
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single { Firebase.firestore }
    singleOf(::VehiclesRemoteDataSource)
    singleOf(::VehiclesRepositoryImpl) bind VehiclesRepository::class
}