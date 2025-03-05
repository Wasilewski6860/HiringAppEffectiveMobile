package ru.hiringapp.main.feature.bottom_navigation

interface OnBottomNavigationItemClickListener {
    val onItemClicked: (BottomNavigationItem) -> Unit
}

interface BottomNavigationItemsClickListener : OnBottomNavigationItemClickListener

class BottomNavigationItemsClickListenerImpl(
    override val onItemClicked: (BottomNavigationItem) -> Unit
) : BottomNavigationItemsClickListener