package ohmry.workus.core;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    @Column
    private LocalDateTime createdTime;
    @Column
    private LocalDateTime updatedTime;
    @Transient
    private boolean isCreated;
    @Transient
    private boolean isUpdated;

    public BaseEntity() {
        this.createdTime = null;
        this.updatedTime = null;
    }

    public boolean isCreated() {
        return this.isCreated;
    }

    public boolean isUpdated() {
        return this.isUpdated;
    }

    @PrePersist
    public void prePersist() {
        this.isCreated = false;
        this.createdTime = LocalDateTime.now();
    }

    @PostPersist
    public void postPersist() {
        this.isCreated = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.isUpdated = false;
        this.updatedTime = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        this.isUpdated = true;
    }
}
