package kh.farrukh.sortmapperexamplejava.lesson;

import java.time.LocalDateTime;
import java.util.Optional;
import kh.farrukh.sortmapperexamplejava.common.exception.NotFoundException;
import kh.farrukh.sortmapperexamplejava.course.CourseService;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonUpdateRequestDTO;
import kh.farrukh.sortmapperexamplejava.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

  private final LessonRepo lessonRepo;
  private final LessonMapper lessonMapper;
  private final TeacherService teacherService;
  private final CourseService courseService;

  @Override
  public void createLesson(LessonCreateRequestDTO requestDTO) {
    var lesson = lessonMapper.toEntity(requestDTO);
    var course = courseService.findCourse(requestDTO.courseId());
    var teacher = Optional.ofNullable(requestDTO.helperTeacherId())
        .map(teacherService::findTeacher)
        .orElse(null);
    lesson.setCourse(course);
    lesson.setHelperTeacher(teacher);
    lessonRepo.save(lesson);
  }

  @Override
  public LessonDetailsResponseDTO getLesson(Long id) {
    return lessonMapper.toDetailsResponseDTO(findLesson(id));
  }

  @Override
  public Page<LessonResponseDTO> getLessons(Pageable pageable) {
    return lessonRepo
        .findAllByDeletedAtIsNull(pageable)
        .map(lessonMapper::toResponseDTO);
  }

  @Override
  public void updateLesson(Long id, LessonUpdateRequestDTO requestDTO) {
    var lesson = findLesson(id);
    lessonMapper.update(lesson, requestDTO);
    var course = courseService.findCourse(requestDTO.courseId());
    var teacher = Optional.ofNullable(requestDTO.helperTeacherId())
        .map(teacherService::findTeacher)
        .orElse(null);
    lesson.setCourse(course);
    lesson.setHelperTeacher(teacher);
    lessonRepo.save(lesson);
  }

  @Override
  public void deleteLesson(Long id) {
    var lesson = findLesson(id);
    lesson.setDeletedAt(LocalDateTime.now());
    lessonRepo.save(lesson);
  }

  @Override
  public LessonEntity findLesson(Long id) {
    return lessonRepo
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new NotFoundException("Lesson not found with id: " + id));
  }

}
