# Отображаемый текст UiText

Компонет позволяет оборачивать различные виды текста для отображения на UI.

## UiText.Raw

Враппер для простого текста в формате `CharSequence`
Может использоватся для оборачивания Spannable текста.

### Пример использования

```kotlin
UiText.Raw(text = "Hello, World!")

val spannable = SpannableString("Hello, World!")
UiText.Raw(text = spannable)
```

## UiText.Resource

Враппер для текста из ресурсов приложения.

### Пример использования

```kotlin
UiText.Resource(resId = R.string.hello_world)

UiText.Resource(
    resId = R.string.hello_world_params,
    args = arrayOf("param1", "param2")
)
```

## UiText.Plurals

Враппер для текста из ресурсов `R.plurals` приложения.

### Пример использования

```kotlin
UiText.Plurals(pluralsResId = R.plurals.time_in_minutes, quantity = 10)

UiText.Plurals(
    pluralsResId = R.plurals.time_in_minutes_params,
    quantity = 10,
    args = arrayOf("param1", "param2")
)
```

## UiText.Stylized

Враппер для текста с хранением стилей для отображения текста на UI.

### Пример использования

```kotlin
UiText.Stylized(
    uiText = UiText.Raw("Hello, World!"),
    styleOptions = StyleOptions(
        textAppearanceResId = R.style.DefaultTitle1,
        colorResId = R.color.colorPrimary
    )
)
```