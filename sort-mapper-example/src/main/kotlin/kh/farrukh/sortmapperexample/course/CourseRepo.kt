package kh.farrukh.sortmapperexample.course

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepo : JpaRepository<CourseEntity, Long> {

    fun findByIdAndDeletedAtIsNull(id: Long): CourseEntity?

    fun findAllByDeletedAtIsNull(pageable: Pageable): Page<CourseEntity>

}