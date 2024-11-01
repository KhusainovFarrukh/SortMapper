package kh.farrukh.sortmapperexamplejava.course.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CourseCreateRequestDTO(
    @NotBlank
    @Size(min = 2, max = 50)
    String title,
    @NotBlank
    @Size(min = 2, max = 200)
    String description,
    @Size(min = 2, max = 100)
    String slogan,
    @NotNull
    Long teacherId,
    @NotNull
    @Positive
    Integer durationMonth,
    @NotNull
    @PositiveOrZero
    Double price
) {

}
