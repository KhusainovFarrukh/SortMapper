package kh.farrukh.sortmapperexamplejava.lesson.model;

import kh.farrukh.sortmapperexamplejava.course.model.CourseResponseDTO;
import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;

public record LessonDetailsResponseDTO(
    Long id,
    String title,
    String description,
    Long courseId,
    CourseResponseDTO course,
    Long helperTeacherId,
    TeacherResponseDTO helperTeacher
) {

}
