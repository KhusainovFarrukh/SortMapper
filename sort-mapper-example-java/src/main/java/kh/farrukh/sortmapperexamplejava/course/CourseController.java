package kh.farrukh.sortmapperexamplejava.course;

import kh.farrukh.sortmapperexamplejava.course.model.CourseCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApi {

  private final CourseService courseService;

  @Override
  public ResponseEntity<PagedModel<CourseResponseDTO>> getCourses(Pageable pageable) {
    return ResponseEntity.ok(new PagedModel<>(courseService.getCourses(pageable)));
  }

  @Override
  public ResponseEntity<CourseDetailsResponseDTO> getCourse(Long id) {
    return ResponseEntity.ok(courseService.getCourse(id));
  }

  @Override
  public ResponseEntity<Void> createCourse(CourseCreateRequestDTO requestDTO) {
    courseService.createCourse(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateCourse(Long id, CourseUpdateRequestDTO requestDTO) {
    courseService.updateCourse(id, requestDTO);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> deleteCourse(Long id) {
    courseService.deleteCourse(id);
    return ResponseEntity.noContent().build();
  }

}
