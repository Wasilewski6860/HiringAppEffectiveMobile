package ru.hiringapp.transport.base

/**
 * Выполнить [Transformable.transform] для каждого элемента коллекции.
 */
fun <T> List<Transformable<T>>.transform(): List<T> =
    map { item -> item.transform() }
