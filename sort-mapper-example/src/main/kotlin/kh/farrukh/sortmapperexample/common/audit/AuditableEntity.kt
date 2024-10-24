package kh.farrukh.sortmapperexample.common.audit

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class AuditableEntity(
    @CreatedBy
    @Column(name = "created_by")
    var createdBy: Long? = null,
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime,
    @LastModifiedBy
    @Column(name = "updated_by")
    var updatedBy: String? = null,
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime,
    @Column(name = "deleted_by")
    var deletedBy: String? = null,
    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
)