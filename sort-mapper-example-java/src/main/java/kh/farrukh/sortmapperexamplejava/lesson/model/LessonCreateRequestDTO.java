package kh.farrukh.sortmapperexamplejava.lesson.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LessonCreateRequestDTO(
    @NotBlank
    @Size(min = 2, max = 50)
    String title,
    @Size(min = 2, max = 200)
    String description,
    @NotNull
    Long courseId,
    Long helperTeacherId
) {

}
