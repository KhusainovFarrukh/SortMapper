package kh.farrukh.sortmapperexample.lesson.model

import kh.farrukh.sortmapperexample.course.model.CourseResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO

data class LessonDetailsResponseDTO(
    val id: Long,
    val title: String,
    val description: String?,
    val courseId: Long,
    val course: CourseResponseDTO,
    val helperTeacherId: Long?,
    val helperTeacher: TeacherResponseDTO?
)
