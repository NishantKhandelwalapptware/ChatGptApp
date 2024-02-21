package com.example.chatgptapp.util

inline fun <T, R : Any> Iterable<T>.safeMap(transform: (T) -> R?): List<R> {
    return mapNotNullTo(ArrayList(), transform)
}