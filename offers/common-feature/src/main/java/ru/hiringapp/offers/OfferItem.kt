package ru.hiringapp.offers

enum class OfferIconState(val id: String) {
    NEAR_VACANCIES("near_vacancies"),
    LEVEL_UP_RESUME("level_up_resume"),
    TEMPORARY_JOB("temporary_job"),
    NONE("");

    companion object {
        fun fromString(value: String?): OfferIconState {
            return entries.find { it.id == value }
                ?: NONE
        }
    }
}

data class OfferItem(
    val iconState: OfferIconState,
    val title: String,
    val buttonText: String?,
    val link: String
)