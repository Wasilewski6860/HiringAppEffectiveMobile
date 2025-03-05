package ru.hiringapp.base.text

data class ClickableText(val fullText: String, val clickableRanges: List<Pair<Int, Int>>)
