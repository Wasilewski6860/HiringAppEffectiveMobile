package ru.hiringapp.search.blocks

import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.search.blocks.offers.OffersMapper
import javax.inject.Inject

internal class BlocksMapper @Inject constructor(
    private val offersMapper: OffersMapper,
) : suspend (List<Offer>) -> List<BlockDataUi> {
    override suspend fun invoke(data: List<Offer>): List<BlockDataUi> {
        val offers = offersMapper(data)
        return listOf(offers)
    }

}
