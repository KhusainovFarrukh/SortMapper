package kh.farrukh.sortmapperexample.teacher.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class TeacherUpdateRequestDTO(
    @field:[NotBlank Size(min = 2, max = 50)]
    val firstName: String,
    @field:[NotBlank Size(min = 2, max = 50)]
    val lastName: String,
    @field:Size(min = 2, max = 50)
    val middleName: String?,
    @field:Positive
    val age: Int
)
