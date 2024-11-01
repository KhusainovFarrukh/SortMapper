package kh.farrukh.sortmapperexamplejava.course;

import java.util.List;
import java.util.Optional;
import kh.farrukh.sortmapperexamplejava.course.model.CourseShortInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Long> {

  Optional<CourseEntity> findByIdAndDeletedAtIsNull(Long id);

  Page<CourseEntity> findAllByDeletedAtIsNull(Pageable pageable);

  @Query("""
      select new kh.farrukh.sortmapperexamplejava.course.model.CourseShortInfoResponseDTO(
        course.id,
        course.title,
        course.teacher.firstName
      )
      from CourseEntity course
      where course.deletedAt is null
      """)
  List<CourseShortInfoResponseDTO> findAllShortInfo(Sort sort);

}
