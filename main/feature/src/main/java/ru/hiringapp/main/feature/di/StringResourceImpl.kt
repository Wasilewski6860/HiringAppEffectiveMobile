package ru.hiringapp.main.feature.di

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.hiringapp.base.resources.StringResources
import javax.inject.Inject

class StringResourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringResources {


    ///---- UTILS SECTION
    private fun string(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    private fun string(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    /**
     * Для выделения ссылок в TextView необходимы начальные и конечные индексы.
     * Возвращает для строки [this] пару начало-конец на основе параметра [linkText].
     * В ином случае возвращает пустой список.
     * Возвращает список, а не одну пару, так как появится необходимость выделять несколько ссылок.
     */
    private fun String.getClickableRanges(linkText: String): List<Pair<Int, Int>> =
        indexOf(linkText).let { startIndex ->
            if (startIndex != -1) {
                val endIndex = startIndex + linkText.length
                listOf(startIndex to endIndex)
            } else {
                emptyList()
            }
        }
}