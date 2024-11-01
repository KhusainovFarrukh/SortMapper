package kh.farrukh.sortmapperexample.lesson

import kh.farrukh.sortmapperexample.lesson.model.LessonCreateRequestDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonUpdateRequestDTO
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LessonController(
    private val lessonService: LessonService
) : LessonApi {

    override fun getAllLessons(pageable: Pageable) =
        ResponseEntity.ok(lessonService.getAllLessons(pageable))

    override fun createLesson(requestDTO: LessonCreateRequestDTO): ResponseEntity<Unit> {
        lessonService.createLesson(requestDTO)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun getLessonById(id: Long) = ResponseEntity.ok(lessonService.getLessonById(id))

    override fun updateLesson(id: Long, requestDTO: LessonUpdateRequestDTO): ResponseEntity<Unit> {
        lessonService.updateLesson(id, requestDTO)
        return ResponseEntity.ok().build()
    }

    override fun deleteLesson(id: Long): ResponseEntity<Unit> {
        lessonService.deleteLesson(id)
        return ResponseEntity.noContent().build()
    }
}