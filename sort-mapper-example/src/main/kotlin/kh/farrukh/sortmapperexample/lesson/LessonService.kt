package kh.farrukh.sortmapperexample.lesson

import kh.farrukh.sortmapperexample.lesson.model.LessonCreateRequestDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonDetailsResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonResponseDTO
import kh.farrukh.sortmapperexample.lesson.model.LessonUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LessonService {

    fun createLesson(requestDTO: LessonCreateRequestDTO)

    fun getLessonById(id: Long): LessonDetailsResponseDTO

    fun getAllLessons(pageable: Pageable): Page<LessonResponseDTO>

    fun updateLesson(id: Long, requestDTO: LessonUpdateRequestDTO)

    fun deleteLesson(id: Long)

}