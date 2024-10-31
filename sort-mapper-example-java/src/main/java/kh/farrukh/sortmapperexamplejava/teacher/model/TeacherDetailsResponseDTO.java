package kh.farrukh.sortmapperexamplejava.teacher.model;

public record TeacherDetailsResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String middleName,
    Integer age
) {

}
