package ru.hiringapp.search.blocks.offers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.hiringapp.base_feature.extensions.dp
import ru.hiringapp.base_feature.view.decoration.EqualHeightItemDecoration
import ru.hiringapp.base_feature.view.decoration.SpacingItemDecorationBuilder
import ru.hiringapp.search.blocks.BlockDataUi
import ru.hiringapp.search.databinding.ItemOffersBinding

internal fun createOffersDelegate(
    onOfferItemClick: (item: BlockDataUi.OfferItem) -> Unit,
) =
    adapterDelegateViewBinding<BlockDataUi.OffersItem, BlockDataUi, ItemOffersBinding>(
        { layoutInflater, root ->
            ItemOffersBinding.inflate(layoutInflater, root, false)
        }
    ) {
        val offersAdapter = createOffersAdapter(onOfferItemClick)
        with(binding.rvOffers) {
            adapter = offersAdapter
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val position = parent.getChildAdapterPosition(view)
                    if (position == 0) {
                        outRect.left = 16.dp
                    }
                    if (position == state.itemCount - 1) {
                        outRect.right = 16.dp
                    }
                }
            })
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State,
                ) {
                    outRect.right = 8.dp
                }
            })

            addItemDecoration(EqualHeightItemDecoration())
            itemAnimator = null
        }

        bind {
            offersAdapter.items = item.offers
        }
    }
