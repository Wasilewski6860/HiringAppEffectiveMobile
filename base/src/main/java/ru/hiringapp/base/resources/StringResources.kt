package ru.hiringapp.base.resources

interface StringResources {

    fun getViewersCountText(viewersCount: Int?): String?

    fun getPublishDateFormatted(day: Int, month: Int): String

    fun getPublishDateFormatted(date: String): String

    fun getAllVacanciesTextFormatted(count: Int): String

    fun getAdditionalVacanciesTextFormatted(count: Int): String
}