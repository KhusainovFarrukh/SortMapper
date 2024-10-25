package kh.farrukh.sortmapperexample.lesson

import kh.farrukh.sortmapperexample.common.exception.NotFoundException
import kh.farrukh.sortmapperexample.course.CourseService
import kh.farrukh.sortmapperexample.lesson.model.LessonCreateRequestDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonDetailsResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonUpdateRequestDTO
import kh.farrukh.sortmapperexample.teacher.TeacherService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LessonServiceImpl(
    private val lessonRepo: LessonRepo,
    private val courseService: CourseService,
    private val teacherService: TeacherService
) : LessonService {

    override fun createLesson(requestDTO: LessonCreateRequestDTO) {
        val course = courseService.findCourse(requestDTO.courseId)
        val helperTeacher = requestDTO.helperTeacherId?.let(teacherService::findTeacher)
        val lesson = requestDTO.toEntity(course, helperTeacher)
        lessonRepo.save(lesson)
    }

    override fun getLessonById(id: Long): LessonDetailsResponseDTO {
        return findLesson(id).toDetailsResponseDTO()
    }

    override fun getAllLessons(pageable: Pageable): Page<LessonResponseDTO> {
        return lessonRepo
            .findAllByDeletedAtIsNull(pageable)
            .map(LessonEntity::toResponseDTO)
    }

    override fun updateLesson(id: Long, requestDTO: LessonUpdateRequestDTO) {
        val lesson = findLesson(id)
        val course = courseService.findCourse(requestDTO.courseId)
        val helperTeacher = requestDTO.helperTeacherId?.let(teacherService::findTeacher)
        lesson.update(requestDTO, course, helperTeacher)
        lessonRepo.save(lesson)
    }

    override fun deleteLesson(id: Long) {
        val lesson = findLesson(id)
        lesson.deletedAt = LocalDateTime.now()
        lessonRepo.save(lesson)
    }

    private fun findLesson(id: Long) = lessonRepo.findByIdAndDeletedAtIsNull(id)
        ?: throw NotFoundException("Lesson not found with id: $id")

}