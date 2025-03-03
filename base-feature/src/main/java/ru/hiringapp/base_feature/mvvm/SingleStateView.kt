package ru.hiringapp.base_feature.mvvm

interface SingleStateView<ScreenState : State> {

    fun initViews()

    fun render(state: ScreenState)
}