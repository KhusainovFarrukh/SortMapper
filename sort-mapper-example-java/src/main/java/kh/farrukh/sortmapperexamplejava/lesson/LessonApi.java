package kh.farrukh.sortmapperexamplejava.lesson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonUpdateRequestDTO;
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

@Tag(name = "Lesson API")
@RequestMapping("api/v1/lessons")
public interface LessonApi {

  @Operation(summary = "Get lessons")
  @GetMapping
  ResponseEntity<PagedModel<LessonResponseDTO>> getLessons(Pageable pageable);

  @Operation(summary = "Get lesson by id")
  @GetMapping("{id}")
  ResponseEntity<LessonDetailsResponseDTO> getLesson(@PathVariable Long id);

  @Operation(summary = "Create lesson")
  @PostMapping
  ResponseEntity<Void> createLesson(@Valid @RequestBody LessonCreateRequestDTO requestDTO);

  @Operation(summary = "Update lesson")
  @PutMapping("{id}")
  ResponseEntity<Void> updateLesson(
      @PathVariable Long id,
      @Valid @RequestBody LessonUpdateRequestDTO requestDTO
  );

  @Operation(summary = "Delete lesson")
  @DeleteMapping("{id}")
  ResponseEntity<Void> deleteLesson(@PathVariable Long id);

}
