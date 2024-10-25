package kh.farrukh.sortmapperexample.lesson

import jakarta.persistence.*
import kh.farrukh.sortmapperexample.common.audit.AuditableEntity
import kh.farrukh.sortmapperexample.course.CourseEntity
import kh.farrukh.sortmapperexample.teacher.TeacherEntity
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "lessons")
@EntityListeners(AuditingEntityListener::class)
class LessonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = LESSON_ID_GENERATOR)
    @SequenceGenerator(
        name = LESSON_ID_GENERATOR,
        sequenceName = LESSON_ID_SEQUENCE,
        allocationSize = 1
    )
    var id: Long? = null,
    @Column(name = "title", nullable = false, length = 50)
    var title: String,
    @Column(name = "description", length = 200)
    var description: String?,
    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    var course: CourseEntity,
    @ManyToOne
    @JoinColumn(name = "helper_teacher_id")
    var helperTeacher: TeacherEntity?
) : AuditableEntity(
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
) {

    companion object {
        const val LESSON_ID_GENERATOR = "lesson_id_generator"
        const val LESSON_ID_SEQUENCE = "lesson_id_sequence"
    }

}


