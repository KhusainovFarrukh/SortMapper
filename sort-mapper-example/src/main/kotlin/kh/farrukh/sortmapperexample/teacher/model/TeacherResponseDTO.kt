package kh.farrukh.sortmapperexample.teacher.model

data class TeacherResponseDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val age: Int
)