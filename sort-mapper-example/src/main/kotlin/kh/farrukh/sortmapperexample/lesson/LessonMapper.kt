package kh.farrukh.sortmapperexample.lesson

import kh.farrukh.sortmapperexample.course.CourseEntity
import kh.farrukh.sortmapperexample.course.toResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonCreateRequestDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonDetailsResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonUpdateRequestDTO
import kh.farrukh.sortmapperexample.teacher.TeacherEntity
import kh.farrukh.sortmapperexample.teacher.toResponseDTO

fun LessonCreateRequestDTO.toEntity(
    course: CourseEntity,
    helperTeacher: TeacherEntity?
) = LessonEntity(
    title = title,
    description = description,
    course = course,
    helperTeacher = helperTeacher
)

fun LessonEntity.toResponseDTO() = LessonResponseDTO(
    id = id!!,
    title = title,
    description = description,
    courseId = course.id!!,
    courseTitle = course.title,
    teacherFirstName = course.teacher.firstName,
    teacherLastName = course.teacher.lastName,
    helperTeacherId = helperTeacher?.id,
    helperTeacherFirstName = helperTeacher?.firstName,
    helperTeacherLastName = helperTeacher?.lastName
)

fun LessonEntity.toDetailsResponseDTO() = LessonDetailsResponseDTO(
    id = id!!,
    title = title,
    description = description,
    courseId = course.id!!,
    course = course.toResponseDTO(),
    helperTeacherId = helperTeacher?.id,
    helperTeacher = helperTeacher?.toResponseDTO()
)

fun LessonEntity.update(
    requestDTO: LessonUpdateRequestDTO,
    course: CourseEntity,
    helperTeacher: TeacherEntity?
) {
    title = requestDTO.title
    description = requestDTO.description
    this.course = course
    this.helperTeacher = helperTeacher
}