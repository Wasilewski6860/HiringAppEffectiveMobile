package ru.hiringapp.base_feature.itemdecoration

import android.graphics.Rect

abstract class DecoratedRecyclerViewItem {

    companion object {
        const val MATCH_SPAN = -1
    }

    open var decorationData: DecorationData = DecorationData()
    open val needDivider: Boolean = false
    val canPlaceDivider: Boolean get() = needDivider && decorationData.isLastInRow

    fun calculateRect(
        items: List<DecoratedRecyclerViewItem>,
        orientation: Orientation = Orientation.VERTICAL,
        maxSpan: Int,
        itemPosition: Int,
    ) {
        val columnStart: Int
        val columnEnd: Int
        var previousSpanSum = 0

        setMatchSpanIfMoreThanMax(maxSpan)

        when {
            decorationData.spanSize == maxSpan -> {
                columnStart = 0
                columnEnd = decorationData.spanSize - 1
            }
            itemPosition == 0 -> {
                columnStart = 0
                columnEnd = decorationData.spanSize - 1
            }
            else -> {
                var i = itemPosition - 1
                val previousItem = items[i]
                if (maxSpan - decorationData.spanSize - previousItem.decorationData.spanSize < 0) {
                    columnStart = 0
                    columnEnd = decorationData.spanSize - 1
                } else {
                    while (i >= 0 && !items[i].decorationData.isLastInRow) {
                        val currentSpanSize = items[i].decorationData.spanSize
                        previousSpanSum += currentSpanSize
                        i--
                    }
                    columnStart = previousSpanSum % maxSpan
                    columnEnd = columnStart + decorationData.spanSize - 1
                }
            }
        }

        val isFirstRow = when (orientation) {
            Orientation.VERTICAL -> {
                if (itemPosition == 0) {
                    true
                } else {
                    var spanSum = 0
                    var isFirst = true
                    for (i in 0 until itemPosition + 1) {
                        val currentSpanSize = items[i].decorationData.spanSize
                        spanSum += currentSpanSize
                        if (spanSum > maxSpan) {
                            isFirst = false
                            break
                        }
                    }
                    isFirst
                }
            }
            Orientation.HORIZONTAL -> {
                columnStart == 0
            }
        }
        val isLastRow = when (orientation) {
            Orientation.VERTICAL -> {
                // Вычисляем свободное пространство последней строки
                val shift = if (items.size % maxSpan != 0) {
                    maxSpan - items.size % maxSpan
                } else {
                    0
                }
                var firstInLastRow = items.lastIndex
                var sum = items[firstInLastRow].decorationData.spanSize.coerceAtLeast(1)
                val lastRowContentSpanSize = maxSpan - shift
                while (sum < lastRowContentSpanSize) {
                    firstInLastRow--
                    sum += items[firstInLastRow].decorationData.spanSize.coerceAtLeast(1)
                }
                itemPosition >= firstInLastRow
            }
            Orientation.HORIZONTAL -> {
                columnEnd == maxSpan - 1
            }
        }

        val isFirstInRow = when (orientation) {
            Orientation.VERTICAL -> {
                columnStart == 0
            }
            Orientation.HORIZONTAL -> {
                if (itemPosition == 0) {
                    true
                } else {
                    var spanSum = 0
                    var isFirst = true
                    for (i in 0 until itemPosition + 1) {
                        val currentSpanSize = items[i].decorationData.spanSize
                        spanSum += currentSpanSize
                        if (spanSum > maxSpan) {
                            isFirst = false
                            break
                        }
                    }
                    isFirst
                }
            }
        }
        val isLastInRow = when (orientation) {
            Orientation.VERTICAL -> {
                columnEnd == maxSpan - 1
            }
            Orientation.HORIZONTAL -> {
                when {
                    items.size <= decorationData.spanSize -> {
                        var spanSum = 0
                        var isLast = true
                        for (i in itemPosition until items.size) {
                            val currentSpanSize = items[i].decorationData.spanSize
                            spanSum += currentSpanSize
                            if (spanSum >= maxSpan) {
                                isLast = false
                                break
                            }
                        }
                        isLast
                    }
                    else -> {
                        if (items.size - itemPosition <= maxSpan) {
                            var spanSum = 0
                            var isLast = true
                            for (i in itemPosition until items.size) {
                                val currentSpanSize = items[i].decorationData.spanSize
                                spanSum += currentSpanSize
                                if (spanSum >= maxSpan) {
                                    isLast = false
                                    break
                                }
                            }
                            isLast
                        } else {
                            false
                        }
                    }
                }
            }
        }

        val spacing = decorationData.insideMargins
        val spacingTop = spacing.top
        val spacingBottom = spacing.bottom
        val spacingLeft = spacing.left
        val spacingRight = spacing.right

        val edges = decorationData.outsideMargins
        val edgeTop = edges.top
        val edgeBottom = edges.bottom
        val edgeLeft = edges.left
        val edgeRight = edges.right

        this.decorationData.isLastInRow = isLastInRow

        if (isFirstRow) {
            if (edgeTop > 0) {
                decorationData.outRect.top = edgeTop
            }

            if (!isLastRow) {
                decorationData.outRect.bottom = spacingBottom
            }
        }

        if (isLastRow) {
            if (edgeBottom > 0) {
                decorationData.outRect.bottom = edgeBottom
            }

            if (!isFirstRow) {
                decorationData.outRect.top = spacingTop
            }
        }

        if (!isFirstRow && !isLastRow) {
            decorationData.outRect.top = spacingTop
            decorationData.outRect.bottom = spacingBottom
        }

        if (isFirstInRow) {
            if (edgeLeft > 0) {
                decorationData.outRect.left = edgeLeft
            }

            if (!isLastInRow) {
                decorationData.outRect.right = spacingRight
            }
        }

        if (isLastInRow) {
            if (edgeRight > 0) {
                decorationData.outRect.right = edgeRight
            }

            if (!isFirstInRow) {
                decorationData.outRect.left = spacingLeft
            }
        }

        if (!isFirstInRow && !isLastInRow) {
            decorationData.outRect.left = spacingLeft
            decorationData.outRect.right = spacingRight
        }
    }

    fun setMatchSpanIfMoreThanMax(maxSpan: Int): Int {
        if (isSpanMoreThanMax(maxSpan)) {
            decorationData.spanSize = maxSpan
        }
        return decorationData.spanSize
    }

    fun isSpanMoreThanMax(maxSpan: Int) =
        decorationData.spanSize > maxSpan || decorationData.spanSize == MATCH_SPAN

    data class DecorationData(
        var itemId: Int? = null,
        var spanSize: Int = MATCH_SPAN,
        var insideMargins: ItemRect = ItemRect(),
        var outsideMargins: ItemRect = ItemRect(),
        var padding: ItemRect? = null,
        var isLastInRow: Boolean = false,
        var typedItemPosition: Int = -1,
        val outRect: Rect = Rect(),
        var scrollState: Int = 0,
    ) {
        fun deepCopy() = DecorationData(
            itemId = this.itemId,
            spanSize = spanSize,
            insideMargins = insideMargins.copy(),
            outsideMargins = outsideMargins.copy(),
            padding = padding?.copy(),
            isLastInRow = isLastInRow,
            typedItemPosition = typedItemPosition,
            outRect = Rect(
                outRect.left,
                outRect.top,
                outRect.right,
                outRect.bottom
            )
        )
    }

    enum class Orientation {
        VERTICAL, HORIZONTAL
    }

    open fun onChanged(newItem: DecoratedRecyclerViewItem): DecoratedRecyclerViewItem {
        return this
    }

    open fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return false
    }

    open fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return false
    }
}

fun <T : DecoratedRecyclerViewItem> List<T>.calculateRect(
    orientation: DecoratedRecyclerViewItem.Orientation = DecoratedRecyclerViewItem.Orientation.VERTICAL,
    maxSpan: Int,
): List<T> {
    forEachIndexed { index, decoratedRecyclerViewItem ->
        decoratedRecyclerViewItem.calculateRect(
            this,
            orientation,
            maxSpan,
            index
        )
    }
    return this
}

fun DecoratedRecyclerViewItem.compareWith(newItem: DecoratedRecyclerViewItem): Boolean {
    val newItemClass = newItem::class.simpleName
    val itemClass = this::class.simpleName
    return if (newItemClass == itemClass) this == newItem else false
}
