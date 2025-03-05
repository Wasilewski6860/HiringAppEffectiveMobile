package ru.hiringapp.base_feature.second_navigation

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

abstract class BaseRoute<S : Fragment>(private val classPath: String) {

    fun getScreenClass(): KClass<out S>? {
        return kotlin.runCatching {
            Class.forName(classPath).kotlin
        }.onFailure {
            if (it is ClassNotFoundException) {
                throw it
            } else {
                error("Unknown error for class: ${this::class.java.name}")
            }
        }.getOrNull() as? KClass<out S>
    }
}
