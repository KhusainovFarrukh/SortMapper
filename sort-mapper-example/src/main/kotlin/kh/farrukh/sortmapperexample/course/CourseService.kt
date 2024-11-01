package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.course.model.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

interface CourseService {

    fun getCourseById(id: Long): CourseDetailsResponseDTO

    fun getCourses(pageable: Pageable): Page<CourseResponseDTO>

    fun createCourse(requestDTO: CourseCreateRequestDTO)

    fun updateCourse(id: Long, requestDTO: CourseUpdateRequestDTO)

    fun deleteCourse(id: Long)

    fun findCourse(id: Long): CourseEntity

    fun getCoursesShortInfo(sort: Sort): List<CourseShortInfoResponseDTO>
}