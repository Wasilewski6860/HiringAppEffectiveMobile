package ru.hiringapp.base.text

import android.content.Context
import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import kotlinx.parcelize.Parcelize

sealed interface UiText : Parcelable {

    fun getText(context: Context): CharSequence

    @Parcelize
    data class Raw(
        val text: CharSequence
    ) : UiText {
        override fun getText(context: Context) = text
    }

    @Parcelize
    data class Resource(
        @StringRes private val resId: Int,
        private val args: Array<String> = emptyArray()
    ) : UiText {
        override fun getText(context: Context) =
            if (args.isEmpty()) {
                context.getString(resId)
            } else {
                context.getString(resId, *args)
            }
    }

    @Parcelize
    data class Plurals(
        @PluralsRes private val pluralsResId: Int,
        private val quantity: Int,
        private val args: Array<String> = emptyArray()
    ) : UiText {
        override fun getText(context: Context) =
            if (args.isEmpty()) {
                context.resources.getQuantityString(pluralsResId, quantity)
            } else {
                context.resources.getQuantityString(pluralsResId, quantity, *args)
            }
    }

    @Parcelize
    data class Stylized(
        val uiText: UiText,
        val styleOptions: StyleOptions
    ) : UiText {
        override fun getText(context: Context) = uiText.getText(context)

        @Parcelize
        data class StyleOptions(
            @StyleRes val appearanceResId: Int,
            @ColorRes val colorResId: Int? = null
        ) : Parcelable
    }

    companion object
}