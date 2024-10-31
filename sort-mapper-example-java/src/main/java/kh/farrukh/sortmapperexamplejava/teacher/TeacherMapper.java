package kh.farrukh.sortmapperexamplejava.teacher;

import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TeacherMapper {

  TeacherResponseDTO toResponseDTO(TeacherEntity entity);

  TeacherDetailsResponseDTO toDetailsResponseDTO(TeacherEntity entity);

  TeacherEntity toEntity(TeacherCreateRequestDTO requestDTO);

  void update(@MappingTarget TeacherEntity entity, TeacherUpdateRequestDTO requestDTO);

}
