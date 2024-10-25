package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.common.exception.NotFoundException
import kh.farrukh.sortmapperexample.course.model.CourseCreateRequestDTO
import kh.farrukh.sortmapperexample.course.model.CourseDetailsResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseResponseDTO
import kh.farrukh.sortmapperexample.course.model.CourseUpdateRequestDTO
import kh.farrukh.sortmapperexample.teacher.TeacherService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CourseServiceImpl(
    private val courseRepo: CourseRepo,
    private val courseMapper: CourseMapper,
    private val teacherService: TeacherService
) : CourseService {

    override fun getCourseById(id: Long): CourseDetailsResponseDTO {
        return findCourse(id)
            .let(courseMapper::toDetailResponseDTO)
    }

    override fun getCourses(pageable: Pageable): Page<CourseResponseDTO> {
        return courseRepo
            .findAllByDeletedAtIsNull(pageable)
            .map(courseMapper::toResponseDTO)
    }

    override fun createCourse(requestDTO: CourseCreateRequestDTO) {
        val course = courseMapper.toEntity(requestDTO)
        course.teacher = teacherService.findTeacher(requestDTO.teacherId)
        courseRepo.save(course)
    }

    override fun updateCourse(id: Long, requestDTO: CourseUpdateRequestDTO) {
        val course = findCourse(id)
        courseMapper.update(course, requestDTO)
        course.teacher = teacherService.findTeacher(requestDTO.teacherId)
        courseRepo.save(course)
    }

    override fun deleteCourse(id: Long) {
        val course = findCourse(id)
        course.deletedAt = LocalDateTime.now()
        courseRepo.save(course)
    }

    fun findCourse(id: Long) = courseRepo.findByIdAndDeletedAtIsNull(id)
        ?: throw NotFoundException("Course not found with id: $id")

}