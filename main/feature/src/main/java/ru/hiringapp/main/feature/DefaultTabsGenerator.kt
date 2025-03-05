package ru.hiringapp.main.feature

import ru.hiringapp.base.UiConstants
import ru.hiringapp.base.resources.DrawableResource
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem
import ru.hiringapp.main.feature.bottom_navigation.SelectionStateResources
import ru.hiringapp.uikit.R as UiKitR

internal fun generateDefaultTabsState(
    initSelectedTab: Int,
    drawableResource: DrawableResource,
    defaultActiveColor: Int,
    defaultInactiveColor: Int,
): List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        id = UiConstants.BottomNavigation.SEARCH_PAGE_ID,
        title = UiKitR.string.bottomNav_search,
        isSelected = initSelectedTab == UiConstants.BottomNavigation.SEARCH_PAGE_ID,
        activeResources = defaultResources(
            isSelected = true,
            drawable = drawableResource.searchButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        ),
        inactiveResources = defaultResources(
            isSelected = false,
            drawable = drawableResource.searchButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        )
    ),
    BottomNavigationItem(
        id = UiConstants.BottomNavigation.FAVOURITES_PAGE_ID,
        title = UiKitR.string.bottomNav_favourites,
        isSelected = initSelectedTab == UiConstants.BottomNavigation.FAVOURITES_PAGE_ID,
        activeResources = defaultResources(
            isSelected = true,
            drawable = drawableResource.favouritesButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        ),
        inactiveResources = defaultResources(
            isSelected = false,
            drawable = drawableResource.favouritesButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        )
    ),
    BottomNavigationItem(
        id = UiConstants.BottomNavigation.FEEDBACK_PAGE_ID,
        title = UiKitR.string.bottomNav_feedback,
        isSelected = initSelectedTab == UiConstants.BottomNavigation.FEEDBACK_PAGE_ID,
        activeResources = defaultResources(
            isSelected = true,
            drawable = drawableResource.feedbackButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        ),
        inactiveResources = defaultResources(
            isSelected = false,
            drawable = drawableResource.feedbackButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        )
    ),
    BottomNavigationItem(
        id = UiConstants.BottomNavigation.MESSAGES_PAGE_ID,
        title = UiKitR.string.bottomNav_messages,
        isSelected = initSelectedTab == UiConstants.BottomNavigation.MESSAGES_PAGE_ID,
        activeResources = defaultResources(
            isSelected = true,
            drawable = drawableResource.messagesButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        ),
        inactiveResources = defaultResources(
            isSelected = false,
            drawable = drawableResource.messagesButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        )
    ),
    BottomNavigationItem(
        id = UiConstants.BottomNavigation.PROFILE_PAGE_ID,
        title = UiKitR.string.bottomNav_profile,
        isSelected = initSelectedTab == UiConstants.BottomNavigation.PROFILE_PAGE_ID,
        activeResources = defaultResources(
            isSelected = true,
            drawable = drawableResource.profileButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        ),
        inactiveResources = defaultResources(
            isSelected = false,
            drawable = drawableResource.profileButtonIcon,
            defaultActiveColor = defaultActiveColor,
            defaultInactiveColor = defaultInactiveColor
        )
    )
)

private fun defaultResources(
    isSelected: Boolean,
    drawable: Int,
    defaultActiveColor: Int,
    defaultInactiveColor: Int,
): SelectionStateResources {
    val color = if (isSelected) defaultActiveColor else defaultInactiveColor
    return SelectionStateResources(
        tint = color,
        textColor = color,
        drawable = drawable
    )
}