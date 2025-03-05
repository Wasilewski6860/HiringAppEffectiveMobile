package ru.hiringapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import ru.hiringapp.database.entity.VacancyEntity.Companion.TABLE_NAME
import ru.hiringapp.domain.vacancy.Address
import ru.hiringapp.domain.vacancy.Experience
import ru.hiringapp.domain.vacancy.Salary
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.transport.base.Transformable

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [
        "id"
    ]
)
data class VacancyEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String, //Needed
    @ColumnInfo(name = "company")
    val company: String, //Needed
    @ColumnInfo(name = "address")
    val address: Address, //Needed just address.town
    @ColumnInfo(name = "experience")
    val experience: Experience, //Needed just experience.previewText
    @ColumnInfo(name = "published_date")
    val publishedDate: String, //Needed
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean, //Needed
    @ColumnInfo(name = "salary")
    val salary: Salary,
    @ColumnInfo(name = "schedules")
    val schedules: List<String>,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "responsibilities")
    val responsibilities: String,
    @ColumnInfo(name = "questions")
    val questions: List<String>,
    @ColumnInfo(name = "looking_number")
    val lookingNumber: Int?, //Needed
    @ColumnInfo(name = "applied_number")
    val appliedNumber: Int?
): Transformable<Vacancy> {

    companion object {
        const val TABLE_NAME = "vacancy"
    }

    override fun transform(): Vacancy {
        return Vacancy(
            id = id,
            title = title,
            company = company,
            address = address,
            experience = experience,
            publishedDate = publishedDate,
            isFavorite = isFavorite,
            salary = salary,
            schedules = schedules,
            description = description,
            responsibilities = responsibilities,
            questions = questions,
            lookingNumber = lookingNumber,
            appliedNumber = appliedNumber
        )
    }
}