package kh.farrukh.sortmapperexample.teacher

import kh.farrukh.sortmapperexample.teacher.model.TeacherCreateRequestDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherDetailsResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherUpdateRequestDTO

fun TeacherEntity.toResponseDTO() = TeacherResponseDTO(
    id = id!!,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    age = age
)

fun TeacherCreateRequestDTO.toEntity() = TeacherEntity(
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    age = age
)

fun TeacherEntity.toDetailsResponseDTO() = TeacherDetailsResponseDTO(
    id = id!!,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    age = age
)

fun TeacherEntity.update(requestDTO: TeacherUpdateRequestDTO) {
    firstName = requestDTO.firstName
    lastName = requestDTO.lastName
    middleName = requestDTO.middleName
    age = requestDTO.age
}