package kh.farrukh.sortmapperexamplejava.lesson;

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
import kh.farrukh.sortmapperexamplejava.course.CourseEntity;
import kh.farrukh.sortmapperexamplejava.teacher.TeacherEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "lessons")
@EntityListeners(AuditingEntityListener.class)
public class LessonEntity extends AuditableEntity {

  private static final String LESSON_ID_GENERATOR = "lesson_id_generator";
  private static final String LESSON_ID_SEQUENCE = "lesson_id_sequence";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = LESSON_ID_GENERATOR)
  @SequenceGenerator(name = LESSON_ID_GENERATOR, sequenceName = LESSON_ID_SEQUENCE, allocationSize = 1)
  private Long id;

  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Column(name = "description", length = 200)
  private String description;

  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  private CourseEntity course;

  @ManyToOne
  @JoinColumn(name = "helper_teacher_id")
  private TeacherEntity helperTeacher;

}
