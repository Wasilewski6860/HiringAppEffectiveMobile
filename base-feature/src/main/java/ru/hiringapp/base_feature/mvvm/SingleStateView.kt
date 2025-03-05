package ru.hiringapp.base_feature.mvvm

interface SingleStateView<ScreenState : State, ScreenEvent : UiEvent> {

    fun initViews()

    fun render(state: ScreenState)

    fun handleUiEvent(event: ScreenEvent)
}