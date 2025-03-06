package ru.hiringapp.search.data

import ru.hiringapp.base_feature.mvvm.State
import ru.hiringapp.search.blocks.BlockDataUi

internal data class SearchUiState(
    val items: List<BlockDataUi> = listOf()
): State