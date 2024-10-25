package kh.farrukh.sortmapperexample.lesson

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepo : JpaRepository<LessonEntity, Long> {

    fun findByIdAndDeletedAtIsNull(id: Long): LessonEntity?

    fun findAllByDeletedAtIsNull(pageable: Pageable): Page<LessonEntity>

}