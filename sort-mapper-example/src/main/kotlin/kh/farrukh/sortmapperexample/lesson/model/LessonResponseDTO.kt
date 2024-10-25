package kh.farrukh.sortmapperexample.lesson.model

data class LessonResponseDTO(
    val id: Long,
    val title: String,
    val description: String?,
    val courseId: Long,
    val courseTitle: String,
    val teacherFirstName: String,
    val teacherLastName: String,
    val helperTeacherId: Long?,
    val helperTeacherFirstName: String?,
    val helperTeacherLastName: String?
)
