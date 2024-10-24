package kh.farrukh.sortmapperexample.teacher

import jakarta.persistence.*
import kh.farrukh.sortmapperexample.common.audit.AuditableEntity
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "teachers")
@EntityListeners(AuditingEntityListener::class)
class TeacherEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TEACHER_ID_GENERATOR)
    @SequenceGenerator(
        name = TEACHER_ID_GENERATOR,
        sequenceName = TEACHER_ID_SEQUENCE,
        allocationSize = 1
    )
    var id: Long? = null,
    @Column(name = "first_name", nullable = false, length = 50)
    var firstName: String,
    @Column(name = "last_name", nullable = false, length = 50)
    var lastName: String,
    @Column(name = "middle_name", length = 50)
    var middleName: String? = null,
    @Column(name = "age", nullable = false)
    var age: Int
) : AuditableEntity(
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
) {

    companion object {
        const val TEACHER_ID_GENERATOR = "teacher_id_generator"
        const val TEACHER_ID_SEQUENCE = "teacher_id_sequence"
    }

}


