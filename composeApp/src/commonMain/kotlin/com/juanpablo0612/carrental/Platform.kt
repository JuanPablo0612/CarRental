package com.juanpablo0612.carrental

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform