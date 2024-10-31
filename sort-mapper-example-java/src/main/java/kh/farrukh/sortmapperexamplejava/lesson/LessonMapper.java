package kh.farrukh.sortmapperexamplejava.lesson;

import kh.farrukh.sortmapperexamplejava.course.CourseMapper;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonUpdateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.TeacherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {CourseMapper.class, TeacherMapper.class}
)
public interface LessonMapper {

  @Mapping(target = "courseId", source = "course.id")
  @Mapping(target = "courseTitle", source = "course.title")
  @Mapping(target = "teacherFirstName", source = "course.teacher.firstName")
  @Mapping(target = "teacherLastName", source = "course.teacher.lastName")
  @Mapping(target = "helperTeacherId", source = "helperTeacher.id")
  @Mapping(target = "helperTeacherFirstName", source = "helperTeacher.firstName")
  @Mapping(target = "helperTeacherLastName", source = "helperTeacher.lastName")
  LessonResponseDTO toResponseDTO(LessonEntity entity);

  LessonDetailsResponseDTO toDetailsResponseDTO(LessonEntity entity);

  LessonEntity toEntity(LessonCreateRequestDTO requestDTO);

  void update(@MappingTarget LessonEntity entity, LessonUpdateRequestDTO requestDTO);

}
