package kh.farrukh.sortmapperexamplejava.teacher;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Teacher API")
@RequestMapping("/api/v1/teacher")
public interface TeacherApi {

  @Operation(summary = "Get all teachers")
  @GetMapping
  ResponseEntity<Page<TeacherResponseDTO>> getTeachers(Pageable pageable);

  @Operation(summary = "Get teacher by id")
  @GetMapping("{id}")
  ResponseEntity<TeacherDetailsResponseDTO> getTeacher(@PathVariable Long id);

  @Operation(summary = "Create teacher")
  @PostMapping
  ResponseEntity<Void> createTeacher(@Valid @RequestBody TeacherCreateRequestDTO requestDTO);

  @Operation(summary = "Update teacher")
  @PutMapping("{id}")
  ResponseEntity<Void> updateTeacher(
      @PathVariable Long id,
      @Valid @RequestBody TeacherUpdateRequestDTO requestDTO
  );

  @Operation(summary = "Delete teacher")
  @DeleteMapping("{id}")
  ResponseEntity<Void> deleteTeacher(@PathVariable Long id);

}
