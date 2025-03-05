package ru.userstory.baucenter.base.ui.text

import android.content.Context
import android.text.Html
import android.widget.TextView
import ru.hiringapp.base.text.UiText

/**
 * Получить текст в формате [String] для отображения на UI
 *
 * @param context контекст android приложения
 */
fun UiText.toString(context: Context): String = getText(context).toString()

/**
 * Получить UiText из HTML текста
 *
 * @param htmlText HTML текст, который будет преобразован в UiText
 *
 * @see android.text.Html.fromHtml
 */
fun UiText.Companion.fromHTML(htmlText: String): UiText.Raw {
    val spanned = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)

    return UiText.Raw(text = spanned)
}

/**
 * Признак того, что текст является пустым или неустановленным
 */
fun UiText.isInconclusive(): Boolean {
    return when (this) {
        is UiText.Raw -> text.isEmpty()
        is UiText.Stylized -> uiText.isInconclusive()
        is UiText.Resource,
        is UiText.Plurals,
            -> false // pluralsResId и resId не может быть null
    }
}

/**
 * Установить текст в [TextView] если он не null
 */
@Deprecated(
    "Может приводить к избыточным проверкам на null",
    ReplaceWith("if (uiText != null) { text = uiText.getText(context) }")
)
fun TextView.setTextIfExist(uiText: UiText?) {
    uiText?.let {
        text = uiText.getText(context)
    }
}

/**
 * Установить текст со стилем в [TextView]
 */
fun TextView.setText(uiText: UiText.Stylized) {
    val options = uiText.styleOptions
    text = uiText.getText(context)
    setTextAppearance(options.appearanceResId)
    options.colorResId?.let { setTextColor(context.getColor(it)) }
}

