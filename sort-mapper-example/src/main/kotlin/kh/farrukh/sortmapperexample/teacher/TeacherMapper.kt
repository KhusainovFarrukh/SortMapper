package kh.farrukh.sortmapperexample.teacher

import kh.farrukh.sortmapperexample.teacher.model.TeacherCreateRequestDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherDetailsResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherUpdateRequestDTO
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.MappingTarget

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface TeacherMapper {

    fun toResponseDTO(teacher: TeacherEntity): TeacherResponseDTO

    fun toEntity(requestDTO: TeacherCreateRequestDTO): TeacherEntity

    fun toDetailsResponseDTO(teacher: TeacherEntity): TeacherDetailsResponseDTO

    fun update(@MappingTarget entity: TeacherEntity, requestDTO: TeacherUpdateRequestDTO)

}