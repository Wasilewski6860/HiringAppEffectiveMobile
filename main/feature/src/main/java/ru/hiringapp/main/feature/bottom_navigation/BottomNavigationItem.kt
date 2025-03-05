package ru.hiringapp.main.feature.bottom_navigation

import androidx.annotation.StringRes
import ru.hiringapp.base.text.UiText
import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem

data class BottomNavigationItem(
    val id: Int,
    @StringRes val title: Int,
    var isSelected: Boolean,
    var badgeCount: Int? = null,
    var activeResources: SelectionStateResources,
    var inactiveResources: SelectionStateResources,
) : DecoratedRecyclerViewItem() {
    val currentResources
        get() = if (isSelected) {
            activeResources
        } else {
            inactiveResources
        }

    val isBadgeVisible get() = badgeCount!=null

    override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return newItem is BottomNavigationItem && newItem.id == id
    }

    override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return newItem is BottomNavigationItem && newItem == this
    }

    fun deepCopy(copyFun: BottomNavigationItem.() -> BottomNavigationItem): BottomNavigationItem {
        return copyFun(this).apply {
            this@apply.activeResources = this@BottomNavigationItem.activeResources.copy()
            this@apply.inactiveResources = this@BottomNavigationItem.inactiveResources.copy()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BottomNavigationItem

        if (id != other.id) return false
        if (title != other.title) return false
        if (isSelected != other.isSelected) return false
        if (badgeCount != other.badgeCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + isSelected.hashCode()
        result = 31 * result + (badgeCount ?: 0)
        return result
    }
}

data class SelectionStateResources(
    val tint: Int?,
    val textColor: Int,
    val drawable: Int,
)