package ru.hiringapp.database

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.hiringapp.domain.offer.Button
import ru.hiringapp.domain.vacancy.Address
import ru.hiringapp.domain.vacancy.Experience
import ru.hiringapp.domain.vacancy.Salary


class ButtonConverter {
    @TypeConverter
    fun fromButton(value: Button) = Json.encodeToString(value)

    @TypeConverter
    fun toButton(value: String): Button = Json.decodeFromString(value)
}

class AddressConverter {
    @TypeConverter
    fun fromAddress(value: Address) = Json.encodeToString(value)

    @TypeConverter
    fun toAddress(value: String): Address = Json.decodeFromString(value)
}

class ExperienceConverter {
    @TypeConverter
    fun fromExperience(value: Experience) = Json.encodeToString(value)

    @TypeConverter
    fun toExperience(value: String): Experience = Json.decodeFromString(value)
}

class SalaryConverter {
    @TypeConverter
    fun fromSalary(value: Salary) = Json.encodeToString(value)

    @TypeConverter
    fun toSalary(value: String): Salary = Json.decodeFromString(value)
}

class StringListConverter {
    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String): List<String> = Json.decodeFromString(value)
}