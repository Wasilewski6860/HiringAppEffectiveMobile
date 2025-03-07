package ru.hiringapp.vacancy

import androidx.core.view.isInvisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.hiringapp.uikit.R
import ru.hiringapp.vacancy.databinding.ItemVacancyBinding

internal fun createVacancyDelegate(
    onVacancyButtonItemClick: (item: VacancyItem) -> Unit,
    onVacancyFavouriteButtonItemClick: (item: VacancyItem) -> Unit,
) = adapterDelegateViewBinding<VacancyItem,
        VacancyItem, ItemVacancyBinding>(
    { layoutInflater, root ->
        ItemVacancyBinding.inflate(layoutInflater, root, false)
    }
) {
    bind {
        with(binding) {
            btnApply.setOnClickListener {
                onVacancyButtonItemClick(item)
            }
            val favouriteDrawable =
                getDrawable(
                    if (item.isFavorite) {
                        R.drawable.ic_favourites_selected
                    } else {
                        R.drawable.ic_favourites_not_selected
                    }
                )
            ivFavourite.setImageDrawable(favouriteDrawable)
            ivFavourite.setOnClickListener {
                onVacancyFavouriteButtonItemClick(item)
            }
            tvViewersCount.isInvisible = true
            item.lookingNumberText?.let {
                tvViewersCount.isInvisible = false
                tvViewersCount.text = it
            }
            tvTitle.text = item.title
            tvAddress.text = item.address
            tvCompany.text = item.company
            tvExperience.text = item.experience
            tvPublishDate.text = item.publishedDate
        }
    }
}