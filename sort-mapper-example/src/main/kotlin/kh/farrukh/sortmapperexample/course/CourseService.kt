package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.course.model.CourseCreateRequestDTO
import kh.farrukh.sortmapperexample.course.model.CourseDetailsResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CourseService {

    fun getCourseById(id: Long): CourseDetailsResponseDTO

    fun getCourses(pageable: Pageable): Page<CourseResponseDTO>

    fun createCourse(requestDTO: CourseCreateRequestDTO)

    fun updateCourse(id: Long, requestDTO: CourseUpdateRequestDTO)

    fun deleteCourse(id: Long)

    fun findCourse(id: Long): CourseEntity
}