package ru.hiringapp.domain.vacancy

data class Vacancy(
    val id: String,
    val title: String, //Needed
    val company: String, //Needed
    val address: Address, //Needed just address.town
    val experience: Experience, //Needed just experience.previewText
    val publishedDate: String, //Needed
    val isFavorite: Boolean, //Needed
    val salary: Salary,
    val schedules: List<String>,
    val description: String,
    val responsibilities: String,
    val questions: List<String>,
    val lookingNumber: Int? = null, //Needed
    val appliedNumber: Int? = null
) {

    val hasLooking: Boolean = lookingNumber != null
}