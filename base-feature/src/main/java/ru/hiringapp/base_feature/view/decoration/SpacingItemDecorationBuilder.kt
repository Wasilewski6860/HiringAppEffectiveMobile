package ru.hiringapp.base_feature.view.decoration

import androidx.recyclerview.widget.LinearLayoutManager
import ru.hiringapp.base_feature.itemdecoration.ItemRect

class SpacingItemDecorationBuilder {
    private var spanCount = 1
    private var spacing = ItemRect()
    private var edges = ItemRect()
    private var orientation = LinearLayoutManager.VERTICAL
    private var containsHeader = false

    fun setSpanCount(spanCount: Int): SpacingItemDecorationBuilder {
        this.spanCount = spanCount
        return this
    }

    fun setSpacing(spacing: Int): SpacingItemDecorationBuilder {
        this.spacing = ItemRect(spacing)
        return this
    }

    fun setSpacing(
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0,
        left: Int = 0
    ): SpacingItemDecorationBuilder {
        this.spacing = ItemRect(top = top, right = right, bottom = bottom, left = left)
        return this
    }

    fun setEdges(edges: Int): SpacingItemDecorationBuilder {
        this.edges = ItemRect(edges)
        return this
    }

    fun setEdges(
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0,
        left: Int = 0
    ): SpacingItemDecorationBuilder {
        this.edges = ItemRect(top = top, right = right, bottom = bottom, left = left)
        return this
    }

    fun setOrientation(orientation: Int): SpacingItemDecorationBuilder {
        this.orientation = orientation
        return this
    }

    fun setContainsHeader(containsHeader: Boolean): SpacingItemDecorationBuilder {
        this.containsHeader = containsHeader
        return this
    }

    fun build(): SpacingItemDecoration {
        return SpacingItemDecoration(
            spanCount = spanCount,
            spacing = spacing,
            edges = edges,
            orientation = orientation,
            containsHeader = containsHeader
        )
    }
}

