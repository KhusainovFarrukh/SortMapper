package kh.farrukh.sortmapperexample.lesson.model

import kh.farrukh.sortmapper.annotation.SortField


data class LessonResponseDTO(
    val id: Long,
    val title: String,
    val description: String?,
    val courseId: Long,
    val courseTitle: String,
    @SortField("course.teacher.firstName")
    val teacherFirstName: String,
    @SortField("course.teacher.lastName")
    val teacherLastName: String,
    val helperTeacherId: Long?,
    val helperTeacherFirstName: String?,
    val helperTeacherLastName: String?
)
