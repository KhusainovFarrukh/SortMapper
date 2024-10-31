package kh.farrukh.sortmapperexamplejava.teacher;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherEntity, Long> {

  Optional<TeacherEntity> findByIdAndDeletedAtIsNull(Long id);

  Page<TeacherEntity> findAllByDeletedAtIsNull(Pageable pageable);

}
