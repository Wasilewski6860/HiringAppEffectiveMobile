package ru.hiringapp.transport.base

/**
 * Интерфейс, указывающий, что объект может быть трансформирован в объект типа T
 */
interface Transformable<T> {
    fun transform(): T
}
