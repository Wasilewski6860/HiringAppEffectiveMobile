package ru.hiringapp.main.feature

import androidx.fragment.app.Fragment
import ru.hiringapp.base.UiConstants
import ru.hiringapp.base.resources.ColorResources
import ru.hiringapp.base.resources.DrawableResource
import ru.hiringapp.base_feature.second_navigation.BaseRoute
import ru.hiringapp.base_feature.second_navigation.NavigationManager
import ru.hiringapp.base_feature.second_navigation.destinations.FavouritesFragmentRoute
import ru.hiringapp.base_feature.second_navigation.destinations.FeedbackFragmentRoute
import ru.hiringapp.base_feature.second_navigation.destinations.MessagesFragmentRoute
import ru.hiringapp.base_feature.second_navigation.destinations.ProfileFragmentRoute
import ru.hiringapp.base_feature.second_navigation.destinations.SearchFragmentRoute
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem

//И тут табы
internal class TabsController(
    private val colorResources: ColorResources,
    private val state: State,
    private val navigationManager: NavigationManager,
    drawableResource: DrawableResource,
    initSelectedTab: Int = UiConstants.BottomNavigation.SEARCH_PAGE_ID,
) {

    private val tabs: List<BottomNavigationItem>
        get() = state.tabs

    private val selectedTab: BottomNavigationItem
        get() = tabs.first { it.isSelected }

    private val selectedTabsQueue: MutableList<Int> = mutableListOf()

    init {
        state.tabs = generateDefaultTabsState(
            initSelectedTab = initSelectedTab,
            drawableResource = drawableResource,
            defaultActiveColor = colorResources.blue,
            defaultInactiveColor = colorResources.grey4
        )
        selectTab(initSelectedTab)
    }

    fun updateSelectedTabOnUI(id: Int) {
        updateTabs(id)
    }

    fun manualSelectTab(tabId: Int) {
        if (selectedTab.id != tabId) {
            selectTab(tabId)
        }
    }

    fun setFavouritesBadgeCount(count: Int) {
        setBadgeCount(UiConstants.BottomNavigation.FAVOURITES_PAGE_ID, count)
    }

    fun clearLast() {
        if (selectedTabsQueue.isNotEmpty()) {
            selectedTabsQueue.removeAt(selectedTabsQueue.lastIndex)
        }

        val lastTabId = selectedTabsQueue.lastOrNull() ?: UiConstants.BottomNavigation.SEARCH_PAGE_ID
        updateTabs(lastTabId)
    }

    fun getSelectedTabScreen(): BaseRoute<Fragment> = getRouteByItem(selectedTab)

    private fun getRouteByItem(bottomNavigationItem: BottomNavigationItem): BaseRoute<Fragment> =
        when (bottomNavigationItem.id) {
            UiConstants.BottomNavigation.SEARCH_PAGE_ID -> SearchFragmentRoute()
            UiConstants.BottomNavigation.FAVOURITES_PAGE_ID -> FavouritesFragmentRoute()
            UiConstants.BottomNavigation.FEEDBACK_PAGE_ID -> FeedbackFragmentRoute()
            UiConstants.BottomNavigation.MESSAGES_PAGE_ID -> MessagesFragmentRoute()
            UiConstants.BottomNavigation.PROFILE_PAGE_ID -> ProfileFragmentRoute()
            else -> error("Неподдерживаемый идентификатор таба")
        }

    @Suppress("SameParameterValue")
    private fun setBadgeCount(id: Int, count: Int) {
        val updatedTabs = tabs.map {
            if (it.id == id) {
                it.deepCopy {
                    it.copy(
                        badgeCount = count
                    )
                }
            } else {
                it
            }
        }
        state.tabs = updatedTabs
    }


    private fun selectTab(tabId: Int) {
        selectedTabsQueue.add(tabId)
        updateTabs(tabId)
        navigationManager.navigateTo(getSelectedTabScreen())
    }

    private fun updateTabs(id: Int) {
        val updatedTabs = tabs.map { tab ->
            tab.copy(isSelected = tab.id == id)
        }
        state.tabs = updatedTabs
    }

    interface State {
        var tabs: List<BottomNavigationItem>
    }
}