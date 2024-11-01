package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.course.model.CourseCreateRequestDTO
import kh.farrukh.sortmapperexample.course.model.CourseUpdateRequestDTO
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController(
    private val courseService: CourseService
) : CourseApi {

    override fun getCourses(pageable: Pageable) =
        ResponseEntity.ok(courseService.getCourses(pageable))

    override fun getCourseById(id: Long) = ResponseEntity.ok(courseService.getCourseById(id))

    override fun createCourse(requestDTO: CourseCreateRequestDTO): ResponseEntity<Unit> {
        courseService.createCourse(requestDTO)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun updateCourse(id: Long, requestDTO: CourseUpdateRequestDTO): ResponseEntity<Unit> {
        courseService.updateCourse(id, requestDTO)
        return ResponseEntity.ok().build()
    }

    override fun deleteCourse(id: Long): ResponseEntity<Unit> {
        courseService.deleteCourse(id)
        return ResponseEntity.noContent().build()
    }

}