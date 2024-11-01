package kh.farrukh.sortmapperexamplejava.lesson;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends JpaRepository<LessonEntity, Long> {

  Optional<LessonEntity> findByIdAndDeletedAtIsNull(Long id);

  Page<LessonEntity> findAllByDeletedAtIsNull(Pageable pageable);

}
