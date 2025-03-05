package ru.hiringapp.base_feature.second_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.hiringapp.base_feature.second_navigation.FragmentNavigationConstants.MAIN_BACKSTACK
import javax.inject.Inject

class NavigationManager @Inject constructor() {

    private var fragmentManager: FragmentManager? = null

    fun setFragmentManager(manager: FragmentManager) {
        this.fragmentManager = manager
    }

    private var defaultContainer: Int = 0 //TODO Добавить обработку ошибок, связанных с тем, что контейнер еще установлен

    fun setNavigationContainer(containerId: Int) {
        defaultContainer = containerId
    }

    fun navigateTo(
        route: BaseRoute<out Fragment>,
        container: Int = defaultContainer,
        backStack: String = MAIN_BACKSTACK,
        args: Bundle? = null
    ) {
        val fragment = route.getScreenClass()?.java?.newInstance()?.apply {
            arguments = args
        } ?: throw IllegalArgumentException("Fragment class not found for route: $route")

        fragmentManager?.beginTransaction()
            ?.replace(container, fragment)
            ?.apply { addToBackStack(backStack) }
            ?.commit()
    }

    fun navigateBack() {
        fragmentManager?.popBackStack()
    }
}
