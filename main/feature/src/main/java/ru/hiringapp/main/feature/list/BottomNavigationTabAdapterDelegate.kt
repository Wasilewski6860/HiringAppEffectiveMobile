package ru.hiringapp.main.feature.list

import android.content.res.ColorStateList
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.hiringapp.base_feature.extensions.decoratedAdapterDiffUtils
import ru.hiringapp.base_feature.extensions.setOnDebounceClickListener
import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItemsClickListener
import ru.hiringapp.main.feature.databinding.ItemBottomNavigationButtonBinding

class BottomNavigationAdapter(bottomNavigationItemsClickListener: BottomNavigationItemsClickListener) :
    AsyncListDifferDelegationAdapter<DecoratedRecyclerViewItem>(
        decoratedAdapterDiffUtils(),
        bottomNavigationTabAdapterDelegate(bottomNavigationItemsClickListener)
    )

fun bottomNavigationTabAdapterDelegate(onBottomNavigationItemsClickListener: BottomNavigationItemsClickListener) =
    adapterDelegateViewBinding<BottomNavigationItem, DecoratedRecyclerViewItem, ItemBottomNavigationButtonBinding>(
        { layoutInflater, parent ->
            ItemBottomNavigationButtonBinding.inflate(layoutInflater, parent, false)
        }
    ) {

        binding.containerMainPage.setOnDebounceClickListener {
            onBottomNavigationItemsClickListener.onItemClicked(item)
        }

        bind {
            with(binding) {
                val title = getString(item.title)
                tvTitle.text = title
                tvTitle.setTextColor(item.currentResources.textColor)
                if (item.isBadgeVisible) {
                    tvBadge.text = item.badgeCount.toString()
                }
                tvBadge.isVisible = item.isBadgeVisible
                imgIcon.setImageResource(item.currentResources.drawable)
                imgIcon.contentDescription = title
                imgIcon.imageTintList =
                    item.currentResources.tint?.let { ColorStateList.valueOf(it) }
            }
        }
    }