package ru.hiringapp.base_feature.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hiringapp.base_feature.itemdecoration.ItemRect

class SpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: ItemRect,
    private val edges: ItemRect = ItemRect(),
    private val orientation: Int = LinearLayoutManager.VERTICAL,
    private val containsHeader: Boolean = false,
) : RecyclerView.ItemDecoration() {
    @SuppressWarnings("CognitiveComplexMethod", "LongMethod", "CyclomaticComplexMethod")
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        var position = parent.getChildAdapterPosition(view) // item position
        var itemCount = parent.adapter?.itemCount ?: 0

        if (containsHeader) {
            if (position == 0) return

            position--
            itemCount--
        }

        val column = position % spanCount

        var isFirstRow = position < spanCount
        var isLastRow =
            if (itemCount % spanCount == 0) {
                position >= itemCount - spanCount
            } else {
                position >= itemCount - itemCount % spanCount
            }
        var isFirstInRow = column == 0
        var isLastInRow = column == spanCount - 1

        val spacingTop = spacing.top
        val spacingBottom = spacing.bottom
        val spacingLeft = spacing.left
        val spacingRight = spacing.right

        val edgeTop = edges.top
        val edgeBottom = edges.bottom
        val edgeLeft = edges.left
        val edgeRight = edges.right

        if (orientation == LinearLayoutManager.HORIZONTAL) {
            isFirstRow = column == 0
            isLastRow = column == spanCount - 1
            isFirstInRow = position < spanCount
            isLastInRow =
                if (itemCount % spanCount == 0) {
                    position >= itemCount - spanCount
                } else {
                    position >= itemCount - itemCount % spanCount
                }
        }

        if (isFirstRow) {
            if (edgeTop > 0) {
                outRect.top = edgeTop
            }

            if (!isLastRow) {
                outRect.bottom = spacingBottom
            }
        }

        if (isLastRow) {
            if (edgeBottom > 0) {
                outRect.bottom = edgeBottom
            }

            if (!isFirstRow) {
                outRect.top = spacingTop
            }
        }

        if (!isFirstRow && !isLastRow) {
            outRect.top = spacingTop
            outRect.bottom = spacingBottom
        }

        if (isFirstInRow) {
            if (edgeLeft > 0) {
                outRect.left = edgeLeft
            }

            if (!isLastInRow) {
                outRect.right = spacingRight
            }
        }

        if (isLastInRow) {
            if (edgeRight > 0) {
                outRect.right = edgeRight
            }

            if (!isFirstInRow) {
                outRect.left = spacingLeft
            }
        }

        if (!isFirstInRow && !isLastInRow) {
            outRect.left = spacingLeft
            outRect.right = spacingRight
        }
    }
}