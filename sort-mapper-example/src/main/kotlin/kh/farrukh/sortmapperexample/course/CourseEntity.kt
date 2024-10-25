package kh.farrukh.sortmapperexample.course

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import kh.farrukh.sortmapperexample.common.audit.AuditableEntity
import kh.farrukh.sortmapperexample.teacher.TeacherEntity
import org.jetbrains.annotations.NotNull
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "courses")
@EntityListeners(AuditingEntityListener::class)
class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COURSE_ID_GENERATOR)
    @SequenceGenerator(
        name = COURSE_ID_GENERATOR,
        sequenceName = COURSE_ID_SEQUENCE,
        allocationSize = 1
    )
    var id: Long? = null,
    @Column(name = "title", nullable = false, length = 50)
    var title: String,
    @Column(name = "description", nullable = false, length = 200)
    var description: String,
    @Column(name = "slogan", length = 100)
    var slogan: String? = null,
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false)
    var teacher: TeacherEntity,
    @Column(name = "duration_month", nullable = false)
    var durationMonth: Int,
    @Column(name = "price", nullable = false)
    var price: Double
) : AuditableEntity(
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
) {

    companion object {
        const val COURSE_ID_GENERATOR = "course_id_generator"
        const val COURSE_ID_SEQUENCE = "course_id_sequence"
    }

}


