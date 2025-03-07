package ru.hiringapp.search.mapper

import ru.hiringapp.base.resources.StringResources
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.VacancyItem
import javax.inject.Inject
import kotlin.math.max

internal data class VacanciesData(
    val vacancyItems: List<VacancyItem>,
    val allVacanciesText: String,
    val additionalVacanciesText: String
)

const val VACANCIES_BLOCK_SIZE = 3

internal class VacanciesMapper @Inject constructor(
    private val stringResources: StringResources
) : (Boolean, List<Vacancy>) -> (VacanciesData) {

    override fun invoke(
        isExpanded: Boolean,
        data: List<Vacancy>,
    ): VacanciesData {
        val neededToMap = if (isExpanded) data else data.take(VACANCIES_BLOCK_SIZE)
        val allVacanciesCount = max(0, data.size)
        val additionalVacanciesCount = max(0, data.size- VACANCIES_BLOCK_SIZE)
        return VacanciesData(
            vacancyItems = neededToMap.map {
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
            allVacanciesText = stringResources.getAllVacanciesTextFormatted(allVacanciesCount),
            additionalVacanciesText = stringResources.getAdditionalVacanciesTextFormatted(additionalVacanciesCount)
        )
    }
}