package kh.farrukh.sortmapperexample.course

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kh.farrukh.sortmapperexample.course.model.CourseCreateRequestDTO
import kh.farrukh.sortmapperexample.course.model.CourseDetailsResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "Course API")
@RequestMapping("/api/v1/courses")
interface CourseApi {

    @GetMapping
    @Operation(summary = "Get all courses")
    fun getCourses(pageable: Pageable): ResponseEntity<Page<CourseResponseDTO>>

    @GetMapping("{id}")
    @Operation(summary = "Get course by ID")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<CourseDetailsResponseDTO>

    @PostMapping
    @Operation(summary = "Create a new course")
    fun createCourse(@Valid @RequestBody requestDTO: CourseCreateRequestDTO): ResponseEntity<Unit>

    @PutMapping("{id}")
    @Operation(summary = "Update course by ID")
    fun updateCourse(
        @PathVariable id: Long,
        @Valid @RequestBody requestDTO: CourseUpdateRequestDTO
    ): ResponseEntity<Unit>

    @DeleteMapping("{id}")
    @Operation(summary = "Delete course by ID")
    fun deleteCourse(@PathVariable id: Long): ResponseEntity<Unit>

}