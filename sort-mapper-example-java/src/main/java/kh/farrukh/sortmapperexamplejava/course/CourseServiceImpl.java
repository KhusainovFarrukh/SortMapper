package kh.farrukh.sortmapperexamplejava.course;

import java.time.LocalDateTime;
import java.util.List;
import kh.farrukh.sortmapperexamplejava.course.model.CourseCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseShortInfoResponseDTO;
import kh.farrukh.sortmapperexamplejava.course.model.CourseUpdateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepo courseRepo;
  private final CourseMapper courseMapper;
  private final TeacherService teacherService;

  @Override
  public void createCourse(CourseCreateRequestDTO requestDTO) {
    var course = courseMapper.toEntity(requestDTO);
    var teacher = teacherService.findTeacher(requestDTO.teacherId());
    course.setTeacher(teacher);
    courseRepo.save(course);
  }

  @Override
  public Page<CourseResponseDTO> getCourses(Pageable pageable) {
    return courseRepo
        .findAllByDeletedAtIsNull(pageable)
        .map(courseMapper::toResponseDTO);
  }

  @Override
  public List<CourseShortInfoResponseDTO> getCoursesShortInfo(Sort sort) {
    return courseRepo.findAllShortInfo(sort);
  }

  @Override
  public CourseDetailsResponseDTO getCourse(Long id) {
    var course = findCourse(id);
    return courseMapper.toDetailsResponseDTO(course);
  }

  @Override
  public void updateCourse(Long id, CourseUpdateRequestDTO requestDTO) {
    var course = findCourse(id);
    courseMapper.update(course, requestDTO);
    var teacher = teacherService.findTeacher(requestDTO.teacherId());
    course.setTeacher(teacher);
    courseRepo.save(course);
  }

  @Override
  public void deleteCourse(Long id) {
    var course = findCourse(id);
    course.setDeletedAt(LocalDateTime.now());
    courseRepo.save(course);
  }

  @Override
  public CourseEntity findCourse(Long id) {
    return courseRepo
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
  }

}
