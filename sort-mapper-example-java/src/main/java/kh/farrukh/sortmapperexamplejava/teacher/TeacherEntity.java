package kh.farrukh.sortmapperexamplejava.teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import kh.farrukh.sortmapperexamplejava.common.audit.AuditableEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "teachers")
@EntityListeners(AuditingEntityListener.class)
public class TeacherEntity extends AuditableEntity {

  private static final String TEACHER_ID_GENERATOR = "teacher_id_generator";
  private static final String TEACHER_ID_SEQUENCE = "teacher_id_sequence";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TEACHER_ID_GENERATOR)
  @SequenceGenerator(name = TEACHER_ID_GENERATOR, sequenceName = TEACHER_ID_SEQUENCE, allocationSize = 1)
  private Long id;

  @Column(name = "first_name", nullable = false, length = 50)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Column(name = "middle_name", length = 50)
  private String middleName;

  @Column(name = "age", nullable = false)
  private Integer age;

}
