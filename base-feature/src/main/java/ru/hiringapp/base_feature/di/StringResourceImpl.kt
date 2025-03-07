package ru.hiringapp.base_feature.di

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.hiringapp.base.resources.StringResources
import ru.hiringapp.base_feature.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class StringResourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringResources {

    private fun string(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    private fun string(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    private fun getMonthPlural(month: Int): String {
        val resId = when (month) {
            1 -> R.plurals.month_january
            2 -> R.plurals.month_february
            3 -> R.plurals.month_march
            4 -> R.plurals.month_april
            5 -> R.plurals.month_may
            6 -> R.plurals.month_june
            7 -> R.plurals.month_july
            8 -> R.plurals.month_august
            9 -> R.plurals.month_september
            10 -> R.plurals.month_october
            11 -> R.plurals.month_november
            else -> R.plurals.month_december
        }
        return context.resources.getQuantityString(resId, month, month)
    }

    override fun getViewersCountText(viewersCount: Int?): String? {
        if (viewersCount==null) return null
        return context.resources.getQuantityString(R.plurals.viewers_count_text, viewersCount, viewersCount)
    }

    override fun getPublishDateFormatted(day: Int, month: Int): String {
        return string(R.string.published_date_format, day, getMonthPlural(month))

    }

    override fun getPublishDateFormatted(date: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val date = LocalDate.parse(date, inputFormatter)

        val day = date.dayOfMonth
        val month = date.monthValue
        return getPublishDateFormatted(day, month)
    }

    override fun getAllVacanciesTextFormatted(count: Int): String {
        return if (count == 0) {
            context.getString(R.string.no_vacancies)
        } else {
            context.resources.getQuantityString(R.plurals.vacancies_count, count, count)
        }
    }

    override fun getAdditionalVacanciesTextFormatted(count: Int): String {
        return if (count == 0) {
            context.getString(R.string.no_vacancies)
        } else {
            context.resources.getQuantityString(R.plurals.more_vacancies, count, count)
        }
    }
}