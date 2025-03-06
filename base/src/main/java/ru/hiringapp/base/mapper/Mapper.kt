package ru.hiringapp.base.mapper

interface Mapper<E, D> {
    fun map(type: E): D
}