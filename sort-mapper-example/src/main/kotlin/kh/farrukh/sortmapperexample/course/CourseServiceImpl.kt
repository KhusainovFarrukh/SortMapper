package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.common.exception.NotFoundException
import kh.farrukh.sortmapperexample.course.model.*
import kh.farrukh.sortmapperexample.teacher.TeacherService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CourseServiceImpl(
    private val courseRepo: CourseRepo,
    private val teacherService: TeacherService
) : CourseService {

    override fun getCourseById(id: Long): CourseDetailsResponseDTO {
        return findCourse(id).toDetailsResponseDTO()
    }

    override fun getCourses(pageable: Pageable): Page<CourseResponseDTO> {
        return courseRepo
            .findAllByDeletedAtIsNull(pageable)
            .map(CourseEntity::toResponseDTO)
    }

    override fun createCourse(requestDTO: CourseCreateRequestDTO) {
        val teacher = teacherService.findTeacher(requestDTO.teacherId)
        val course = requestDTO.toEntity(teacher)
        courseRepo.save(course)
    }

    override fun updateCourse(id: Long, requestDTO: CourseUpdateRequestDTO) {
        val course = findCourse(id)
        val teacher = teacherService.findTeacher(requestDTO.teacherId)
        course.update(requestDTO, teacher)
        courseRepo.save(course)
    }

    override fun deleteCourse(id: Long) {
        val course = findCourse(id)
        course.deletedAt = LocalDateTime.now()
        courseRepo.save(course)
    }

    override fun findCourse(id: Long) = courseRepo.findByIdAndDeletedAtIsNull(id)
        ?: throw NotFoundException("Course not found with id: $id")

    override fun getCoursesShortInfo(sort: Sort): List<CourseShortInfoResponseDTO> {
        return courseRepo.findAllShortInfo(sort)
    }

}