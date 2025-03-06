package ru.hiringapp.search.blocks.offers

import android.view.View
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.hiringapp.search.blocks.BlockDataUi
import ru.hiringapp.search.blocks.OfferIconState
import ru.hiringapp.search.databinding.ItemOfferBinding
import ru.hiringapp.uikit.R as UiKitR

internal fun createOfferDelegate(
    onProductServiceItemClick: (item: BlockDataUi.OfferItem) -> Unit,
) = adapterDelegateViewBinding<BlockDataUi.OfferItem,
        BlockDataUi.OfferItem, ItemOfferBinding>(
    { layoutInflater, root ->
        ItemOfferBinding.inflate(layoutInflater, root, false)
    }
) {
    bind {
        with(binding) {
            root.setOnClickListener {
                onProductServiceItemClick(item)
            }
            ivAvatar.apply {
                isVisible = true
                when (item.iconState) {
                    OfferIconState.NEAR_VACANCIES -> {
                        setIconDrawable(getDrawable(UiKitR.drawable.ic_feedback))
                    }
                    OfferIconState.LEVEL_UP_RESUME -> {
                        setIconDrawable(getDrawable(UiKitR.drawable.ic_star))
                    }
                    OfferIconState.TEMPORARY_JOB -> {
                        setIconDrawable(getDrawable(UiKitR.drawable.ic_checklist))
                    }
                    OfferIconState.NONE -> {
                        visibility = View.INVISIBLE
                    }
                }
            }
            if (!item.buttonText.isNullOrEmpty()) {
                tvButton.isVisible = true
                tvButton.text = item.buttonText
                tvTitle.maxLines = 2
            } else {
                tvButton.isVisible = false
                tvTitle.maxLines = 2
            }
            tvTitle.text = item.title
        }
    }
}