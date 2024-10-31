package kh.farrukh.sortmapperexamplejava.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kh.farrukh.sortmapperexamplejava.course.model.CourseCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseUpdateRequestDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Course API")
@RequestMapping("/api/v1/courses")
public interface CourseApi {

  @GetMapping
  @Operation(summary = "Get all courses")
  ResponseEntity<PagedModel<CourseResponseDTO>> getCourses(Pageable pageable);

  @GetMapping("{id}")
  @Operation(summary = "Get course by id")
  ResponseEntity<CourseDetailsResponseDTO> getCourse(@PathVariable Long id);

  @PostMapping
  @Operation(summary = "Create course")
  ResponseEntity<Void> createCourse(@Valid @RequestBody CourseCreateRequestDTO requestDTO);

  @PutMapping("{id}")
  @Operation(summary = "Update course by id")
  ResponseEntity<Void> updateCourse(
      @PathVariable Long id,
      @Valid @RequestBody CourseUpdateRequestDTO requestDTO
  );

  @DeleteMapping("{id}")
  @Operation(summary = "Delete course by id")
  ResponseEntity<Void> deleteCourse(@PathVariable Long id);

}
