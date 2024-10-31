package kh.farrukh.sortmapperexamplejava.lesson;

import kh.farrukh.sortmapperexamplejava.lesson.model.LessonCreateRequestDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonDetailsResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonResponseDTO;
import kh.farrukh.sortmapperexamplejava.lesson.model.LessonUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {

  void createLesson(LessonCreateRequestDTO requestDTO);

  LessonDetailsResponseDTO getLesson(Long id);

  Page<LessonResponseDTO> getLessons(Pageable pageable);

  void updateLesson(Long id, LessonUpdateRequestDTO requestDTO);

  void deleteLesson(Long id);

  LessonEntity findLesson(Long id);

}
