package kh.farrukh.sortmapperexamplejava.course;

import kh.farrukh.sortmapperexamplejava.course.model.CourseCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseUpdateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.TeacherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = TeacherMapper.class
)
public interface CourseMapper {

  @Mapping(target = "teacherId", source = "teacher.id")
  @Mapping(target = "teacherFirstName", source = "teacher.firstName")
  @Mapping(target = "teacherLastName", source = "teacher.lastName")
  CourseResponseDTO toResponseDTO(CourseEntity entity);

  CourseDetailsResponseDTO toDetailsResponseDTO(CourseEntity entity);

  CourseEntity toEntity(CourseCreateRequestDTO requestDTO);

  void update(@MappingTarget CourseEntity entity, CourseUpdateRequestDTO requestDTO);

}
