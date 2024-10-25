package kh.farrukh.sortmapperexample.course.model

data class CourseResponseDTO(
    val id: Long,
    val title: String,
    val description: String,
    val slogan: String?,
    val teacherId: Long,
    val teacherFirstName: String,
    val teacherLastName: String,
    val durationMonth: Int,
    val price: Double
)
