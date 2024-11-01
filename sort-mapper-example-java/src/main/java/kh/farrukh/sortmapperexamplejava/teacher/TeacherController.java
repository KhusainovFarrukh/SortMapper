package kh.farrukh.sortmapperexamplejava.teacher;

import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeacherController implements TeacherApi {

  private final TeacherService teacherService;

  @Override
  public ResponseEntity<Page<TeacherResponseDTO>> getTeachers(Pageable pageable) {
    return ResponseEntity.ok(teacherService.getTeachers(pageable));
  }

  @Override
  public ResponseEntity<TeacherDetailsResponseDTO> getTeacher(Long id) {
    return ResponseEntity.ok(teacherService.getTeacher(id));
  }

  @Override
  public ResponseEntity<Void> createTeacher(TeacherCreateRequestDTO requestDTO) {
    teacherService.createTeacher(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateTeacher(Long id, TeacherUpdateRequestDTO requestDTO) {
    teacherService.updateTeacher(id, requestDTO);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> deleteTeacher(Long id) {
    teacherService.deleteTeacher(id);
    return ResponseEntity.noContent().build();
  }

}
