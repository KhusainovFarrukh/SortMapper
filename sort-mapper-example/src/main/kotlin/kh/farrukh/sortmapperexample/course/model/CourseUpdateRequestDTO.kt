package kh.farrukh.sortmapperexample.course.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

data class CourseUpdateRequestDTO(
    @[NotBlank Size(min = 2, max = 50)]
    val title: String,
    @[NotBlank Size(min = 2, max = 200)]
    val description: String,
    @Size(min = 2, max = 100)
    val slogan: String?,
    val teacherId: Long,
    @Positive
    val durationMonth: Int,
    @PositiveOrZero
    val price: Double
)
