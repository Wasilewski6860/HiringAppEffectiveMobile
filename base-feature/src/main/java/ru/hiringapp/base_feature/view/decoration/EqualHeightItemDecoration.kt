package ru.hiringapp.base_feature.view.decoration

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class EqualHeightItemDecoration : ItemDecoration() {

    private var maxHeight = 0
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)

            val widthSpec = View.MeasureSpec.makeMeasureSpec(child.width, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

            child.measure(widthSpec, heightSpec)

            if (child.measuredHeight > maxHeight) {
                maxHeight = child.measuredHeight
            }
        }

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams
            if (layoutParams.height != maxHeight) {
                layoutParams.height = maxHeight
                child.layoutParams = layoutParams
            }
        }
    }
}