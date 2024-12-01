package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {
    @Column(name ="created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(name="created_at",updatable=false)
    private ZonedDateTime createdAt;

    @Column(name="updated_by")
    private String updatedBy;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
