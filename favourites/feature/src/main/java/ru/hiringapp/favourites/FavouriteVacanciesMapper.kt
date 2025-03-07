package ru.hiringapp.favourites

import ru.hiringapp.base.resources.StringResources
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.VacancyItem
import javax.inject.Inject
import kotlin.math.max

internal data class FavouriteVacanciesData(
    val vacancyItems: List<VacancyItem>,
    val allVacanciesText: String
)

internal class FavouriteVacanciesMapper @Inject constructor(
    private val stringResources: StringResources
) : ( List<Vacancy>) -> (FavouriteVacanciesData) {

    override fun invoke(
        data: List<Vacancy>,
    ): FavouriteVacanciesData {
        val allVacanciesCount = max(0, data.size)
        return FavouriteVacanciesData(
            vacancyItems = data.map {
                VacancyItem(
                    id = it.id,
                    lookingNumberText = stringResources.getViewersCountText(it.lookingNumber),
                    isFavorite = it.isFavorite,
                    title = it.title,
                    address = it.address.town,
                    company = it.company,
                    experience = it.experience.previewText,
                    publishedDate = stringResources.getPublishDateFormatted(it.publishedDate)
                )
            },
            allVacanciesText = stringResources.getAllVacanciesTextFormatted(allVacanciesCount)
        )
    }
}