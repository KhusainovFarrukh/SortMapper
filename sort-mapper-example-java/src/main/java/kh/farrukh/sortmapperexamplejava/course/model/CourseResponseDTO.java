package kh.farrukh.sortmapperexamplejava.course.model;

public record CourseResponseDTO(
    Long id,
    String title,
    String description,
    String slogan,
    Long teacherId,
    String teacherFirstName,
    String teacherLastName,
    Integer durationMonth,
    Double price
) {

}
