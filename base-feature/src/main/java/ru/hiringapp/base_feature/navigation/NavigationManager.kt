package ru.hiringapp.base_feature.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.hiringapp.base_feature.navigation.FragmentNavigationConstants.MAIN_BACKSTACK
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
        route: NavigationRoute,
        container: Int = defaultContainer,
        backStack: String = MAIN_BACKSTACK,
        args: Bundle? = null
    ) {
        when (route) {
            is FragmentRoute<*> -> navigateToFragment(route, container, backStack, args)
            is ExternalUrlRoute -> openUrl(route.url)
        }
    }

    private fun navigateToFragment(
        route: FragmentRoute<out Fragment>,
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

    private fun openUrl(url: String) {
        val context = fragmentManager?.fragments?.firstOrNull()?.context
            ?: throw IllegalStateException("No valid context available for opening URL")

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun navigateBack() {
        fragmentManager?.popBackStack()
    }
}
