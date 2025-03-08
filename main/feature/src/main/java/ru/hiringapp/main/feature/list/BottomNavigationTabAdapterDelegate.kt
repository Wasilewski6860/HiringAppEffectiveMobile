package ru.hiringapp.main.feature.list

import android.content.res.ColorStateList
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.hiringapp.base_feature.extensions.setOnDebounceClickListener
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItemsClickListener
import ru.hiringapp.main.feature.databinding.ItemBottomNavigationButtonBinding

class BottomNavigationAdapter(bottomNavigationItemsClickListener: BottomNavigationItemsClickListener) :
    AsyncListDifferDelegationAdapter<BottomNavigationItem>(
        object : DiffUtil.ItemCallback<BottomNavigationItem>() {
            override fun areItemsTheSame(
                oldItem: BottomNavigationItem,
                newItem: BottomNavigationItem
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: BottomNavigationItem,
                newItem: BottomNavigationItem
            ): Boolean {
                return oldItem == newItem
            }
        },
        bottomNavigationTabAdapterDelegate(bottomNavigationItemsClickListener)
    )

fun bottomNavigationTabAdapterDelegate(onBottomNavigationItemsClickListener: BottomNavigationItemsClickListener) =
    adapterDelegateViewBinding<BottomNavigationItem, BottomNavigationItem, ItemBottomNavigationButtonBinding>(
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