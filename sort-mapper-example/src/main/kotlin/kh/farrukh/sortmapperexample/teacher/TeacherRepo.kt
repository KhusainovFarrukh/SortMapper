package kh.farrukh.sortmapperexample.teacher

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepo : JpaRepository<TeacherEntity, Long> {

    fun findByIdAndDeletedAtIsNull(id: Long): TeacherEntity?

    fun findAllByDeletedAtIsNull(pageable: Pageable): Page<TeacherEntity>

}