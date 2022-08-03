package com.tester

actual class Platform {
    actual fun printHello() {
        println("Hello jvm!")
    }
}