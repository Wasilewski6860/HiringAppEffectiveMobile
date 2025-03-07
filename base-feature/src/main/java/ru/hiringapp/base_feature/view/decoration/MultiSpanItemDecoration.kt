package ru.hiringapp.base_feature.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.hiringapp.base_feature.extensions.setPadding
import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem

class MultiSpanItemDecoration<T : DecoratedRecyclerViewItem>() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        getItemAsDecoratedRecyclerViewItem(
            getAdapter(parent),
            parent.getChildAdapterPosition(view)
        )?.let { item ->
            outRect.set(item.decorationData.outRect)
            item.decorationData.padding?.let {
                view.setPadding(it)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getAdapter(parent: RecyclerView): AsyncListDifferDelegationAdapter<T> {
        return (parent.adapter as? AsyncListDifferDelegationAdapter<T>)
            ?: throw IllegalArgumentException(
                "Passed wrong adapter type! " +
                        "Pass AsyncListDifferDelegationAdapter"
            )
    }

    private fun getItemAsDecoratedRecyclerViewItem(
        adapter: AsyncListDifferDelegationAdapter<T>,
        position: Int
    ): DecoratedRecyclerViewItem? {
        return adapter.items.getOrNull(position)
    }
}