package ru.hiringapp.base_feature.di

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.hiringapp.base.resources.ColorResources
import ru.hiringapp.uikit.R
import javax.inject.Inject

class ColorResourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ColorResources {

    override val white: Int get() = color(R.color.white)

    override val black: Int get() = color(R.color.black)

    override val grey1: Int get() = color(R.color.grey_1)

    override val grey2: Int get() = color(R.color.grey_2)

    override val grey3: Int get() = color(R.color.grey_3)

    override val grey4: Int get() = color(R.color.grey_4)

    override val grey5: Int get() = color(R.color.grey_5)

    override val red: Int get() = color(R.color.red)

    override val darkGreen: Int get() = color(R.color.dark_green)

    override val green: Int get() = color(R.color.green)

    override val blue: Int get() = color(R.color.blue)

    override val darkBlue: Int get() = color(R.color.dark_blue)

    @ColorInt
    private fun color(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }
}