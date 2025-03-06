package ru.hiringapp.base_feature.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import ru.hiringapp.base_feature.R
import ru.hiringapp.base_feature.databinding.WidgetSearchAppbarBinding
import ru.hiringapp.uikit.R as UiKitR

class SearchAppBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding: WidgetSearchAppbarBinding =
        WidgetSearchAppbarBinding.bind(inflate(context, R.layout.widget_search_appbar, this))

    private var hintText: String = ""
    private var iconDrawable: Drawable? = null

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SearchAppBar).run {
            iconDrawable = getDrawable(R.styleable.SearchAppBar_actionIconDrawable)
                ?: AppCompatResources.getDrawable(context, UiKitR.drawable.ic_search_not_selected)
            val textResId = getResourceId(R.styleable.SearchAppBar_hintText, 0)
            if (textResId != 0) {
                hintText = context.getString(textResId)
            } else {
                hintText = context.getString(UiKitR.string.searchAppBar_hint_standart)
            }
            recycle()
        }
        with(binding) {
            imgActionIcon.setImageDrawable(iconDrawable)
            etSearch.hint = hintText
        }
    }

    fun setHintText(text: String) {
        hintText = text
        binding.etSearch.hint = hintText
    }

    fun setHintText(textResId: Int) {
        hintText = context.getString(textResId)
        binding.etSearch.hint = hintText
    }

    fun setActionDrawable(resId: Int) {
        iconDrawable =
            AppCompatResources.getDrawable(context, UiKitR.drawable.ic_search_not_selected)
        binding.imgActionIcon.setImageDrawable(iconDrawable)
    }
}