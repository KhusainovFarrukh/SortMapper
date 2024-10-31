package kh.farrukh.sortmapperexamplejava.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import kh.farrukh.sortmapperexamplejava.common.audit.AuditableEntity;
import kh.farrukh.sortmapperexamplejava.teacher.TeacherEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "courses")
@EntityListeners(AuditingEntityListener.class)
public class CourseEntity extends AuditableEntity {

  private static final String COURSE_ID_GENERATOR = "course_id_generator";
  private static final String COURSE_ID_SEQUENCE = "course_id_sequence";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COURSE_ID_GENERATOR)
  @SequenceGenerator(name = COURSE_ID_GENERATOR, sequenceName = COURSE_ID_SEQUENCE, allocationSize = 1)
  private Long id;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "description", nullable = false, length = 200)
  private String description;

  @Column(name = "slogan", length = 100)
  private String slogan;

  @ManyToOne(optional = false)
  @JoinColumn(name = "teacher_id", nullable = false)
  private TeacherEntity teacher;

  @Column(name = "duration_month", nullable = false)
  private Integer durationMonth;

  @Column(name = "price", nullable = false)
  private Double price;

}
