package kh.farrukh.sortmapperexamplejava.lesson.model;

import kh.farrukh.sortmapper.annotation.SortField;

public record LessonResponseDTO(
    Long id,
    String title,
    String description,
    Long courseId,
    String courseTitle,
    @SortField("course.teacher.firstName")
    String teacherFirstName,
    @SortField("course.teacher.lastName")
    String teacherLastName,
    Long helperTeacherId,
    String helperTeacherFirstName,
    String helperTeacherLastName
) {

}
