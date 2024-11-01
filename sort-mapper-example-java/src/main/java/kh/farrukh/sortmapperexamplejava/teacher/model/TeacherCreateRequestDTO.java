package kh.farrukh.sortmapperexamplejava.teacher.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TeacherCreateRequestDTO(
    @NotBlank
    @Size(min = 2, max = 50)
    String firstName,
    @NotBlank
    @Size(min = 2, max = 50)
    String lastName,
    @Size(min = 2, max = 50)
    String middleName,
    @Positive
    Integer age
) {

}
