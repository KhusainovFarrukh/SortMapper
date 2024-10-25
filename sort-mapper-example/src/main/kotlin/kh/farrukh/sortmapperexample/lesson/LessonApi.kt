package kh.farrukh.sortmapperexample.lesson

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kh.farrukh.sortmapperexample.lesson.model.LessonCreateRequestDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonDetailsResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonUpdateRequestDTO
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "Lesson API")
@RequestMapping("/api/v1/lessons")
interface LessonApi {

    @GetMapping
    @Operation(summary = "Get all lessons")
    fun getAllLessons(pageable: Pageable): ResponseEntity<PagedModel<LessonResponseDTO>>

    @PostMapping
    @Operation(summary = "Create a lesson")
    fun createLesson(@Valid @RequestBody requestDTO: LessonCreateRequestDTO): ResponseEntity<Unit>

    @GetMapping("{id}")
    @Operation(summary = "Get a lesson by id")
    fun getLessonById(@PathVariable id: Long): ResponseEntity<LessonDetailsResponseDTO>

    @PutMapping("{id}")
    @Operation(summary = "Update a lesson by id")
    fun updateLesson(
        @PathVariable id: Long,
        @Valid @RequestBody requestDTO: LessonUpdateRequestDTO
    ): ResponseEntity<Unit>

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a lesson by id")
    fun deleteLesson(@PathVariable id: Long): ResponseEntity<Unit>

}