package kh.farrukh.sortmapperexamplejava.course;

import java.util.List;
import kh.farrukh.sortmapperexamplejava.course.model.CourseCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseShortInfoResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CourseService {

  void createCourse(CourseCreateRequestDTO requestDTO);

  Page<CourseResponseDTO> getCourses(Pageable pageable);

  List<CourseShortInfoResponseDTO> getCoursesShortInfo(Sort sort);

  CourseDetailsResponseDTO getCourse(Long id);

  void updateCourse(Long id, CourseUpdateRequestDTO requestDTO);

  void deleteCourse(Long id);

  CourseEntity findCourse(Long id);

}
