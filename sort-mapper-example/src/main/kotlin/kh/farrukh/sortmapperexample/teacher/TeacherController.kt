package kh.farrukh.sortmapperexample.teacher

import kh.farrukh.sortmapperexample.teacher.model.TeacherCreateRequestDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherDetailsResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherUpdateRequestDTO
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TeacherController(
    private val teacherService: TeacherService
) : TeacherApi {

    override fun getTeachers(pageable: Pageable): ResponseEntity<PagedModel<TeacherResponseDTO>> =
        ResponseEntity.ok(PagedModel(teacherService.getTeachers(pageable)))

    override fun createTeacher(requestDTO: TeacherCreateRequestDTO): ResponseEntity<Unit> {
        teacherService.createTeacher(requestDTO)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun getTeacherById(id: Long): ResponseEntity<TeacherDetailsResponseDTO> =
        ResponseEntity.ok(teacherService.getTeacherById(id))

    override fun updateTeacher(
        id: Long,
        requestDTO: TeacherUpdateRequestDTO
    ): ResponseEntity<Unit> {
        teacherService.updateTeacher(id, requestDTO)
        return ResponseEntity.ok().build()
    }

    override fun deleteTeacher(id: Long): ResponseEntity<Unit> {
        teacherService.deleteTeacher(id)
        return ResponseEntity.noContent().build()
    }

}