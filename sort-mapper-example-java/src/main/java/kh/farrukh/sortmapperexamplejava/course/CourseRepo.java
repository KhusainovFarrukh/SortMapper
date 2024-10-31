package kh.farrukh.sortmapperexamplejava.course;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Long> {

  Optional<CourseEntity> findByIdAndDeletedAtIsNull(Long id);

  Page<CourseEntity> findAllByDeletedAtIsNull(Pageable pageable);

}
