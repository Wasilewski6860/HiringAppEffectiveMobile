package ru.hiringapp.offers

import android.view.View
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.hiringapp.offers.databinding.ItemOfferBinding
import ru.hiringapp.uikit.R as UiKitR

internal fun createOfferDelegate(
    onOfferItemClick: (item: OfferItem) -> Unit,
) = adapterDelegateViewBinding<OfferItem,
        OfferItem, ItemOfferBinding>(
    { layoutInflater, root ->
        ItemOfferBinding.inflate(layoutInflater, root, false)
    }
) {
    bind {
        with(binding) {
            root.setOnClickListener {
                onOfferItemClick(item)
            }
            ivAvatar.apply {
                isVisible = true
                when (item.iconState) {
                    OfferIconState.NEAR_VACANCIES -> {
//                      TODO Вставить иконку когда появится в дизайне
                        setCircleColor(getColor(UiKitR.color.blue))
                    }
                    OfferIconState.LEVEL_UP_RESUME -> {
                        setIconDrawable(getDrawable(UiKitR.drawable.ic_star))
                        setCircleColor(getColor(UiKitR.color.dark_green))
                    }
                    OfferIconState.TEMPORARY_JOB -> {
                        setIconDrawable(getDrawable(UiKitR.drawable.ic_checklist))
                        setCircleColor(getColor(UiKitR.color.dark_green))
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
                tvTitle.maxLines = 3
            }
            tvTitle.text = item.title
        }
    }
}