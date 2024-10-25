package kh.farrukh.sortmapperexample.lesson.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class LessonCreateRequestDTO(
    @field:[NotBlank Size(min = 2, max = 50)]
    val title: String,
    @field:Size(min = 2, max = 200)
    val description: String?,
    val courseId: Long,
    val helperTeacherId: Long?
)
