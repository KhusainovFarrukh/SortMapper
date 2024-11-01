package kh.farrukh.sortmapperexamplejava.common.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity {

  @CreatedBy
  @Column(name = "created_by")
  private Long createdBy;

  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedBy
  @Column(name = "updated_by")
  private Long updatedBy;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @Column(name = "deleted_by")
  private Long deletedBy;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

}
