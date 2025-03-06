package ru.hiringapp.search.interactor.impl.mapper

import ru.hiringapp.base.mapper.Mapper
import ru.hiringapp.database.entity.OfferEntity
import ru.hiringapp.domain.offer.Offer
import javax.inject.Inject

class OfferToOfferEntityMapper @Inject constructor() : Mapper<Offer, OfferEntity> {
    override fun map(type: Offer): OfferEntity {
        with(type) {
            return OfferEntity(
                id = id,
                title = title,
                description = description,
                link = link,
                button = button?.text
            )
        }
    }
}