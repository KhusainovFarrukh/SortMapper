package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.course.model.CourseCreateRequestDTO
import kh.farrukh.sortmapperexample.course.model.CourseDetailsResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseUpdateRequestDTO
import kh.farrukh.sortmapperexample.teacher.TeacherMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel
import org.mapstruct.MappingTarget

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = [TeacherMapper::class]
)
interface CourseMapper {

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "teacherFirstName", source = "teacher.firstName")
    @Mapping(target = "teacherLastName", source = "teacher.lastName")
    fun toResponseDTO(entity: CourseEntity): CourseResponseDTO

    @Mapping(target = "teacherId", source = "teacher.id")
    fun toDetailResponseDTO(entity: CourseEntity): CourseDetailsResponseDTO

    fun toEntity(requestDTO: CourseCreateRequestDTO): CourseEntity

    fun update(@MappingTarget entity: CourseEntity, requestDTO: CourseUpdateRequestDTO)

}