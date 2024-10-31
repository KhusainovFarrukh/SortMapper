package kh.farrukh.sortmapperexamplejava.course.model;

import kh.farrukh.sortmapperexamplejava.teacher.model.TeacherResponseDTO;

public record CourseDetailsResponseDTO(
    Long id,
    String title,
    String description,
    String slogan,
    Long teacherId,
    TeacherResponseDTO teacher,
    Integer durationMonth,
    Double price
) {

}
