package ru.hiringapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import ru.hiringapp.database.entity.OfferEntity.Companion.TABLE_NAME
import ru.hiringapp.domain.offer.Button
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.transport.base.Transformable

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [
        "title"
    ]
)
data class OfferEntity(
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "button")
    val button: Button?
): Transformable<Offer> {

    companion object {
        const val TABLE_NAME = "offer"
    }

    override fun transform(): Offer {
        return Offer(
            id = id,
            title = title,
            link = link,
            button = button
        )
    }
}