package kh.farrukh.sortmapperexample.teacher

import kh.farrukh.sortmapperexample.teacher.model.TeacherCreateRequestDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherDetailsResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TeacherService {

    fun createTeacher(requestDTO: TeacherCreateRequestDTO)

    fun getTeacherById(id: Long): TeacherDetailsResponseDTO

    fun getTeachers(pageable: Pageable): Page<TeacherResponseDTO>

    fun updateTeacher(id: Long, requestDTO: TeacherUpdateRequestDTO)

    fun deleteTeacher(id: Long)

}