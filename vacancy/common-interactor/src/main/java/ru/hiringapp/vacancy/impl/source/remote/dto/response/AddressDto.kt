package ru.hiringapp.vacancy.impl.source.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.vacancy.Address
import ru.hiringapp.transport.base.Transformable

@Serializable
data class AddressDto(
    @SerialName("text")
    val town: String,
    @SerialName("street")
    val street: String,
    @SerialName("house")
    val house: String
) : Transformable<Address> {
    override fun transform(): Address {
        return Address(
            town = town,
            street = street,
            house = house
        )
    }
}