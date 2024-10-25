package kh.farrukh.sortmapperexample.course.model

import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO

data class CourseDetailsResponseDTO(
    val id: Long,
    val title: String,
    val description: String,
    val slogan: String?,
    val teacherId: Long,
    val teacher: TeacherResponseDTO,
    val durationMonth: Int,
    val price: Double
)
