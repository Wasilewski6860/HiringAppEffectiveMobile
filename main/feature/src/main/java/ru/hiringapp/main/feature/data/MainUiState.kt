package ru.hiringapp.main.feature.data

import ru.hiringapp.base_feature.mvvm.State
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem

data class MainUiState(
    val tabs: List<BottomNavigationItem> = emptyList(),
    val isBlockingLoading: Boolean = false
) : State {
    val selectedTab: BottomNavigationItem
        get() {
            return tabs.firstOrNull { it.isSelected }
                ?: error("Не должно быть ситуации, когда не выбран хотя бы один таб")
        }
}