package kh.farrukh.sortmapperexamplejava.teacher;

import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

  void createTeacher(TeacherCreateRequestDTO requestDTO);

  void updateTeacher(Long id, TeacherUpdateRequestDTO requestDTO);

  void deleteTeacher(Long id);

  TeacherDetailsResponseDTO getTeacher(Long id);

  Page<TeacherResponseDTO> getTeachers(Pageable pageable);

  TeacherEntity findTeacher(Long id);

}
