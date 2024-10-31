package kh.farrukh.sortmapperexamplejava.teacher.model;

public record TeacherResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String middleName,
    Integer age
) {

}
