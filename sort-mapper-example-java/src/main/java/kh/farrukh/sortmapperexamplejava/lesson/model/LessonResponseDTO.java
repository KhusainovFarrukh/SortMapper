package kh.farrukh.sortmapperexamplejava.lesson.model;

public record LessonResponseDTO(
    Long id,
    String title,
    String description,
    Long courseId,
    String courseTitle,
    String teacherFirstName,
    String teacherLastName,
    Long helperTeacherId,
    String helperTeacherFirstName,
    String helperTeacherLastName
) {

}
