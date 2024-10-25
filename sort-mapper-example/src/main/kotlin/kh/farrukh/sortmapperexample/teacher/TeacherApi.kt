package kh.farrukh.sortmapperexample.teacher

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kh.farrukh.sortmapperexample.teacher.model.TeacherCreateRequestDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherDetailsResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherUpdateRequestDTO
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Teacher API")
@RequestMapping("/api/v1/teachers")
interface TeacherApi {

    @Operation(summary = "Get all teachers")
    @GetMapping
    fun getTeachers(pageable: Pageable): ResponseEntity<PagedModel<TeacherResponseDTO>>

    @Operation(summary = "Create a new teacher")
    @PostMapping
    fun createTeacher(@Valid @RequestBody requestDTO: TeacherCreateRequestDTO): ResponseEntity<Unit>

    @Operation(summary = "Get teacher by ID")
    @GetMapping("{id}")
    fun getTeacherById(@PathVariable id: Long): ResponseEntity<TeacherDetailsResponseDTO>

    @Operation(summary = "Update teacher by ID")
    @PutMapping("{id}")
    fun updateTeacher(
        @PathVariable id: Long,
        @Valid @RequestBody requestDTO: TeacherUpdateRequestDTO
    ): ResponseEntity<Unit>

    @Operation(summary = "Delete teacher by ID")
    @DeleteMapping("{id}")
    fun deleteTeacher(@PathVariable id: Long): ResponseEntity<Unit>

}