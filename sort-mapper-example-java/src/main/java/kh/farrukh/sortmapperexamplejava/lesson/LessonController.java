package kh.farrukh.sortmapperexamplejava.lesson;

import kh.farrukh.sortmapperexamplejava.lesson.model.LessonCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LessonController implements LessonApi {

  private final LessonService lessonService;

  @Override
  public ResponseEntity<PagedModel<LessonResponseDTO>> getLessons(Pageable pageable) {
    return ResponseEntity.ok(new PagedModel<>(lessonService.getLessons(pageable)));
  }

  @Override
  public ResponseEntity<LessonDetailsResponseDTO> getLesson(Long id) {
    return ResponseEntity.ok(lessonService.getLesson(id));
  }

  @Override
  public ResponseEntity<Void> createLesson(LessonCreateRequestDTO requestDTO) {
    lessonService.createLesson(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateLesson(Long id, LessonUpdateRequestDTO requestDTO) {
    lessonService.updateLesson(id, requestDTO);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> deleteLesson(Long id) {
    lessonService.deleteLesson(id);
    return ResponseEntity.ok().build();
  }

}
