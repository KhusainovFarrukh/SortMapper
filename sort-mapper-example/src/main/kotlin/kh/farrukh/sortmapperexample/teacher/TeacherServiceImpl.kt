package kh.farrukh.sortmapperexample.teacher

import kh.farrukh.sortmapperexample.common.exception.NotFoundException
import kh.farrukh.sortmapperexample.teacher.model.TeacherCreateRequestDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherDetailsResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherResponseDTO
import kh.farrukh.sortmapperexample.teacher.model.TeacherUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TeacherServiceImpl(
    private val teacherRepo: TeacherRepo,
    private val teacherMapper: TeacherMapper
) : TeacherService {

    override fun createTeacher(requestDTO: TeacherCreateRequestDTO) {
        val teacherEntity = teacherMapper.toEntity(requestDTO)
        teacherRepo.save(teacherEntity)
    }

    override fun getTeacherById(id: Long): TeacherDetailsResponseDTO {
        return findTeacher(id)
            .let(teacherMapper::toDetailsResponseDTO)
    }

    override fun getTeachers(pageable: Pageable): Page<TeacherResponseDTO> {
        return teacherRepo
            .findAllByDeletedAtIsNull(pageable)
            .map(teacherMapper::toResponseDTO)
    }

    override fun updateTeacher(id: Long, requestDTO: TeacherUpdateRequestDTO) {
        val teacherEntity = findTeacher(id)
        teacherMapper.update(teacherEntity, requestDTO)
        teacherRepo.save(teacherEntity)
    }

    override fun deleteTeacher(id: Long) {
        val teacherEntity = findTeacher(id)
        teacherEntity.deletedAt = LocalDateTime.now()
        teacherRepo.save(teacherEntity)
    }

    override fun findTeacher(id: Long): TeacherEntity =
        teacherRepo.findByIdAndDeletedAtIsNull(id)
            ?: throw NotFoundException("Teacher not found with id: $id")

}