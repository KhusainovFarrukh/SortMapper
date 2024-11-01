package kh.farrukh.sortmapperexample.course

import kh.farrukh.sortmapperexample.course.model.CourseShortInfoResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepo : JpaRepository<CourseEntity, Long> {

    fun findByIdAndDeletedAtIsNull(id: Long): CourseEntity?

    fun findAllByDeletedAtIsNull(pageable: Pageable): Page<CourseEntity>

    @Query(
        """
           select new kh.farrukh.sortmapperexample.course.model.CourseShortInfoResponseDTO(
                course.id,
                course.title,
                course.teacher.firstName
           )
           from CourseEntity course
           where course.deletedAt is null
        """
    )
    fun findAllShortInfo(sort: Sort): List<CourseShortInfoResponseDTO>

}