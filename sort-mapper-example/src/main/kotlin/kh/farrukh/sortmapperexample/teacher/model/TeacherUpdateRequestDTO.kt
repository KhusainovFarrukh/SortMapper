package kh.farrukh.sortmapperexample.teacher.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class TeacherUpdateRequestDTO(
    @field:[NotBlank Size(min = 2, max = 50)]
    private val _firstName: String?,
    @field:[NotBlank Size(min = 2, max = 50)]
    private val _lastName: String?,
    @field:Size(min = 2, max = 50)
    private val _middleName: String?,
    @field:[NotNull Positive]
    private val _age: Int?
) {
    val firstName: String
        get() = _firstName!!

    val lastName: String
        get() = _lastName!!

    val middleName: String?
        get() = _middleName

    val age: Int
        get() = _age!!
}
