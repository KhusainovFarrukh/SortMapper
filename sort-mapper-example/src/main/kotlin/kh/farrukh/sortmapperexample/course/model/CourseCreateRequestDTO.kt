package kh.farrukh.sortmapperexample.course.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

data class CourseCreateRequestDTO(
    @field:[NotBlank Size(min = 2, max = 50)]
    val title: String,
    @field:[NotBlank Size(min = 2, max = 200)]
    val description: String,
    @field:Size(min = 2, max = 100)
    val slogan: String?,
    val teacherId: Long,
    @field:Positive
    val durationMonth: Int,
    @field:PositiveOrZero
    val price: Double
)
