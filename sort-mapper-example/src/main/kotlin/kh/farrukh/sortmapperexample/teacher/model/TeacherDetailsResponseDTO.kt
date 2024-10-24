package kh.farrukh.sortmapperexample.teacher.model

data class TeacherDetailsResponseDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val age: Int
) {

    val fullName: String
        get() = "$firstName ${middleName ?: ""} $lastName"

}
