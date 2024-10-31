package kh.farrukh.sortmapperexamplejava.teacher;

import java.time.LocalDateTime;
import kh.farrukh.sortmapperexamplejava.common.exception.NotFoundException;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepo teacherRepo;
  private final TeacherMapper teacherMapper;

  @Override
  public void createTeacher(TeacherCreateRequestDTO requestDTO) {
    var teacher = teacherMapper.toEntity(requestDTO);
    teacherRepo.save(teacher);
  }

  @Override
  public void updateTeacher(Long id, TeacherUpdateRequestDTO requestDTO) {
    var teacher = findTeacher(id);
    teacherMapper.update(teacher, requestDTO);
    teacherRepo.save(teacher);
  }

  @Override
  public void deleteTeacher(Long id) {
    var teacher = findTeacher(id);
    teacher.setDeletedAt(LocalDateTime.now());
    teacherRepo.save(teacher);
  }

  @Override
  public TeacherDetailsResponseDTO getTeacher(Long id) {
    var teacher = findTeacher(id);
    return teacherMapper.toDetailsResponseDTO(teacher);
  }

  @Override
  public Page<TeacherResponseDTO> getTeachers(Pageable pageable) {
    return teacherRepo
        .findAllByDeletedAtIsNull(pageable)
        .map(teacherMapper::toResponseDTO);
  }

  @Override
  public TeacherEntity findTeacher(Long id) {
    return teacherRepo
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new NotFoundException("Teacher not found with id: " + id));
  }

}
