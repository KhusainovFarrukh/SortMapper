package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.course.model.CourseCreateRequestDTO
import kh.farrukh.sortmapperexample.course.model.CourseDetailsResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseUpdateRequestDTO
import kh.farrukh.sortmapperexample.teacher.TeacherEntity
import kh.farrukh.sortmapperexample.teacher.toResponseDTO

fun CourseCreateRequestDTO.toEntity(teacher: TeacherEntity) = CourseEntity(
    title = title,
    description = description,
    slogan = slogan,
    teacher = teacher,
    durationMonth = durationMonth,
    price = price
)

fun CourseEntity.toResponseDTO() = CourseResponseDTO(
    id = id!!,
    title = title,
    description = description,
    slogan = slogan,
    teacherId = teacher.id!!,
    teacherFirstName = teacher.firstName,
    teacherLastName = teacher.lastName,
    durationMonth = durationMonth,
    price = price
)

fun CourseEntity.toDetailsResponseDTO() = CourseDetailsResponseDTO(
    id = id!!,
    title = title,
    description = description,
    slogan = slogan,
    teacherId = teacher.id!!,
    teacher = teacher.toResponseDTO(),
    durationMonth = durationMonth,
    price = price
)

fun CourseEntity.update(requestDTO: CourseUpdateRequestDTO, teacher: TeacherEntity) {
    title = requestDTO.title
    description = requestDTO.description
    slogan = requestDTO.slogan
    durationMonth = requestDTO.durationMonth
    price = requestDTO.price
    this.teacher = teacher
}